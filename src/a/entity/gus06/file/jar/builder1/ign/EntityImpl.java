package a.entity.gus06.file.jar.builder1.ign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.HashSet;
import java.util.Set;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, V, E {

	public String creationDate() {return "20170108";}

	
	public static int BUFFER = 2048;
	
	
	private File jarFile;
	private JarOutputStream jos;
	private String mainClass;
	private File[] bin;
	private Map entryMap;
	private Set done;
    
    

	public EntityImpl() throws Exception
	{
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("jarFile"))
		{
			jarFile = (File) obj;
			return;
		}
		if(key.equals("mainClass"))
		{
			mainClass = (String) obj;
			return;
		}
		if(key.equals("entryMap"))
		{
			entryMap = (Map) obj;
			return;
		}
		if(key.equals("bin"))
		{
			bin = toFiles(obj);
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private File[] toFiles(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof File[]) return (File[]) obj;
 		if(obj instanceof File) return new File[]{(File) obj};
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	

	
	public void e() throws Exception
	{
		start();	
		try{build();}
		finally{end();}
	}
	
	
	private void start()
	{send(this,"start()");}
	
	private void end()
	{send(this,"end()");}
	
	
	
	
	
	
	public void build() throws Exception
	{
		Manifest mf = new Manifest();
		mf.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
		if(mainClass!=null) mf.getMainAttributes().put(Attributes.Name.MAIN_CLASS, mainClass);
        
		File parent = jarFile.getParentFile();
		if(!parent.exists()) parent.mkdirs();

		FileOutputStream fos = new FileOutputStream(jarFile);
		jos = new JarOutputStream(fos,mf);
		
		done = new HashSet();
        
		// handle entry map
		if(entryMap!=null) addEntryMap();
        
		// handle bin
		if(bin!=null)
		for(int i=0;i<bin.length;i++) addBin(bin[i],i);	
        
		jos.close();
		
		jarFile = null;
		mainClass = null;
		bin = null;
		entryMap = null;
	}
	

					
	private void addEntryMap() throws Exception
	{
		Iterator it = entryMap.keySet().iterator();
		while(it.hasNext())
		{
			String entryName = (String) it.next();
			File file = (File) entryMap.get(entryName);
			if(file==null) throw new Exception("Null file for entry name: "+entryName);
			addEntry(entryName,file);
		}
	}
    
    
		
	
	private void addBin(File root, int i) throws Exception
	{
		if(root==null) throw new Exception("bin["+i+"] is null");
		if(!root.exists()) throw new Exception("bin["+i+"]: "+root.getAbsolutePath()+" does not exist");

		ArrayList fileList = new ArrayList();
		scanDir(root,fileList);
		
		int length = root.getAbsolutePath().length();

		Iterator it_ = fileList.iterator();
		while(it_.hasNext())
		{
			File file = (File) it_.next();
			String path = file.getAbsolutePath();
			String entryName = path.substring(length);
			addEntry(entryName,file);
		}
	}
		
			
			
			
	private void addEntry(String entryName, File file) throws Exception
	{
		if(!file.isFile() || file.length()==0) return;
		
		entryName = formatEntryName(entryName);
		if(done.contains(entryName)) return;
		
		jos.putNextEntry(new JarEntry(entryName));
		done.add(entryName);
		
		FileInputStream fis = null;
		
		try
		{
			int b;
			byte data[] = new byte[BUFFER];
			fis = new FileInputStream(file);
			while((b = fis.read(data,0,BUFFER)) != -1) jos.write(data,0,b);
		}
		finally
		{
			jos.closeEntry();
			if(fis!=null) fis.close();
		}
	}


			

    
	private String getExt(File file)
	{
		String name = file.getName();
		if(!name.contains(".")) return "";
        
		String[] n = name.split("\\.");
		return "."+n[n.length-1].toLowerCase();
	}
    
    
    
    
	private void scanDir(File dir, ArrayList list)
	{
		File[] f = dir.listFiles();
		for(int i=0;i<f.length;i++)
		{
			if(f[i].isFile()) list.add(f[i]);
			else scanDir(f[i],list);
		}
	}
	
	
	
	
	
	private String formatEntryName(String entryName)
	{
		entryName = entryName.replace(File.separatorChar,'/');
		if(entryName.startsWith("/")) return entryName.substring(1);
		return entryName;
	}
}
