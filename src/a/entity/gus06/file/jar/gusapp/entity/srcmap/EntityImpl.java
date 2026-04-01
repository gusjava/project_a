package a.entity.gus06.file.jar.gusapp.entity.srcmap;

import a.framework.*;
import java.util.jar.JarFile;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200208";}


	private Service isToString;

	public EntityImpl() throws Exception
	{
		isToString = Outside.service(this,"gus06.io.transfer.tostring.autodetect");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		File jarFile = (File) obj;
		JarFile jar = new JarFile(jarFile,true,JarFile.OPEN_READ);
		
		Map map = new HashMap();
		Enumeration en = jar.entries();
		while(en.hasMoreElements())
		{
			JarEntry entry = (JarEntry) en.nextElement();
			String entryName = entry.getName();
        	
			if(!entry.isDirectory() && entryName.startsWith("gus06/entity/") && entryName.endsWith("/EntityImpl.java"))
			{
				InputStream is = jar.getInputStream(entry);
				String src = (String) isToString.t(is);
				
				String entityName = entryName.substring(13,entryName.length()-16).replace("/",".");
				map.put(entityName,src);
			}
		}
		jar.close();
		return map;
	}
}