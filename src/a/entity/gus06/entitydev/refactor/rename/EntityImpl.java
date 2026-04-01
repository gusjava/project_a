package a.entity.gus06.entitydev.refactor.rename;

import java.io.File;
import java.io.FileFilter;
import java.io.PrintStream;
import java.util.Map;
import java.util.regex.Pattern;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140708";}
	
	
	public static final String PACKAGE_START = "gus06.entity.";
	
	private FileFilter fileFilter = new FileFilter() {
		public boolean accept(File f) {return f.isFile();}
	};
	

	private Service hasFile;
	private Service readFile;
	
	public EntityImpl() throws Exception
	{
		hasFile = Outside.service(this,"gus06.dir.children.hasfile");
		readFile = Outside.service(this,"gus06.file.read.string");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String name1 = get1(map,REFACTOR.KEY_ENTITYNAME1);
		String name2 = get1(map,REFACTOR.KEY_ENTITYNAME2);
		File rootDir = new File(get1(map,REFACTOR.KEY_ROOTDIR));
		
		renameEntity(rootDir,name1,name2);
	}
	
	
	
	
	private void renameEntity(File rootDir, String name1, String name2) throws Exception
	{
		try
		{
			File dir1 = findPackageDir(rootDir,name1);
			File dir2 = findPackageDir(rootDir,name2);

			if(!hasFile.f(dir1)) throw new Exception("Directory should contain files: "+dir1);
			if(hasFile.f(dir2)) throw new Exception("Directory should not contain files: "+dir2);

			dir2.mkdirs();

			File[] ff = dir1.listFiles(fileFilter);
			for(File f:ff)
			{
				if(isJavaFile(f)) moveJavaFile(f,dir2,name1,name2);
				else moveFile(f,dir2);
			}

			clear(dir1);
		}
		catch(Exception e)
		{
			String message = "Failed to rename entity "+name1+" into "+name2;
			throw new Exception(message,e);
		}
	}
	
	
	
	
	
	
	
	private boolean isJavaFile(File f)
	{return f.getName().toLowerCase().endsWith(".java");}
	
	
	
	
	
	private void moveJavaFile(File file, File dir, String name1, String name2) throws Exception
	{
		String src = (String) readFile.t(file);
		
		String p1 = "package "+PACKAGE_START+name1+";";
		String p2 = "package "+PACKAGE_START+name2+";";
		src = src.replaceFirst(Pattern.quote(p1),p2);
		
		File file2 = new File(dir,file.getName());
		PrintStream p = new PrintStream(file2);
		p.print(src);
		p.close();
		
		boolean r = file.delete();
		if(!r) throw new Exception("Could not delete file "+file);
	}
	
	
	
	
	private void moveFile(File file, File dir) throws Exception
	{
		File file2 = new File(dir,file.getName());
		boolean r = file.renameTo(file2);
		if(!r) throw new Exception("Could not rename file "+file);
	}
	
	
	
	
	private File findPackageDir(File rootDir, String name) throws Exception
	{
		String path = (PACKAGE_START+name).replace(".",File.separator);
		return new File(rootDir,path);
	}
	
	
	
	
	private String get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key))
			throw new Exception("Key not found inside map: "+key);
		return (String) map.get(key);
	}
	
	
	
	
	private void clear(File dir)
	{
		while(dir!=null && dir.list().length==0)
		{
			dir.delete();
			dir = dir.getParentFile();
		}
	}
}
