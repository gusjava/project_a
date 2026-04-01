package a.entity.gus06.file.jar.modifier1;

import a.framework.*;
import java.io.File;
import java.util.jar.Manifest;
import java.util.jar.JarInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.jar.JarOutputStream;
import java.io.FileOutputStream;
import java.util.jar.JarEntry;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170401";}
	
	public static int BUFFERSIZE = 2048;
	
	private Service findInputStream;

	public EntityImpl() throws Exception
	{
		findInputStream = Outside.service(this,"gus06.find.inputstream");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File jarFile0 = (File) o[0];
		File jarFile1 = (File) o[1];
		Map map = (Map) o[2];
		
		
		FileInputStream fis = new FileInputStream(jarFile0);
		BufferedInputStream bis = new BufferedInputStream(fis);
		JarInputStream jis = new JarInputStream(bis);
		Manifest mf = jis.getManifest();
		
		FileOutputStream fos = new FileOutputStream(jarFile1);
		JarOutputStream jos = new JarOutputStream(fos,mf);
		
		JarEntry entry;
		while((entry = jis.getNextJarEntry()) != null)
		{
			String name = entry.getName();
			if(!map.containsKey(name)) addEntry(entry,jos,jis);
		}
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String name = (String) it.next();
			Object data = map.get(name);
			InputStream is = findInputStream(data);
			addEntry(new JarEntry(name),jos,is);
			is.close();
		}
		
		jis.close();
		jos.close();
	}
	
	
	
	private void addEntry(JarEntry entry, JarOutputStream jos, InputStream is) throws Exception
	{
		jos.putNextEntry(entry);
		if(!entry.isDirectory()) write(jos,is);
		jos.closeEntry();
	}
	
	private void write(OutputStream os, InputStream is) throws Exception
	{
		int b;
       		byte data[] = new byte[BUFFERSIZE];
		while((b = is.read(data,0,BUFFERSIZE)) != -1)
		{os.write(data, 0, b);}
	}
	
	private InputStream findInputStream(Object obj) throws Exception
	{return (InputStream) findInputStream.t(obj);}
}
