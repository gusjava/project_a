package a.entity.gus06.file.jar.find.mavenpom;

import a.framework.*;
import java.util.*;
import java.io.File;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251230";}

	private Service isToString;
	private Service xmlToPom;

	public EntityImpl() throws Exception
	{
		isToString = Outside.service(this,"gus06.io.transfer.tostring.utf8");
		xmlToPom = Outside.service(this,"gus06.y.maven1.xmltopom");
	}

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		JarFile jarFile = new JarFile(file,true,JarFile.OPEN_READ);
		
		String pom = null;
		Enumeration en = jarFile.entries();
		while(en.hasMoreElements())
		{
			JarEntry entry = (JarEntry) en.nextElement();
			if(isPOM(entry.getName()))
			{
				InputStream is = jarFile.getInputStream(entry);
				pom = (String) isToString.t(is);
				break;
			}
		}
		jarFile.close();
		return xmlToPom.t(pom);
	}
	
	private boolean isPOM(String name)
	{return name.endsWith("/pom.xml");}
}
