package a.entity.gus06.file.jar.findentries;

import a.framework.*;
import java.io.File;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.List;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140807";}

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		JarFile jarFile = new JarFile(file,true,JarFile.OPEN_READ);

		List list = new ArrayList();
		Enumeration en = jarFile.entries();
		while(en.hasMoreElements())
		{
			JarEntry entry = (JarEntry) en.nextElement();
			list.add(entry.getName());
		}
		jarFile.close();
		
		Collections.sort(list);
		return list;
	}
}
