package a.entity.gus06.file.jar.string.finder1.g;

import a.framework.*;
import java.io.File;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.Map;
import java.util.HashMap;
import java.io.InputStream;
import java.util.Enumeration;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250319";}


	private Service isToString;

	public EntityImpl() throws Exception
	{
		isToString = Outside.service(this,"gus06.convert.inputstreamtostring.autodetect");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String root = (String) o[1];
		
		JarFile jarFile = new JarFile(file,true,JarFile.OPEN_READ);
		Map map = new HashMap();
		int rootLen = root.length();

		Enumeration en = jarFile.entries();
		while(en.hasMoreElements())
		{
			JarEntry entry = (JarEntry)en.nextElement();
			String name = entry.getName();
        	
			if(!entry.isDirectory() && name.startsWith(root))
			{
				String relName = name.substring(rootLen);
				map.put(relName, new Wrapper(file, entry));
			}
		}
		jarFile.close();
		return map;
	}
	
	
	private class Wrapper implements G
	{
		private File file;
		private JarEntry entry;
		
		public Wrapper(File file, JarEntry entry)
		{
			this.file = file;
			this.entry = entry;
		}
		
		public Object g() throws Exception
		{
			JarFile jarFile = new JarFile(file,true,JarFile.OPEN_READ);
			InputStream is = jarFile.getInputStream(entry);
			String text = (String) isToString.t(is);
			is.close();
			jarFile.close();
			return text;
		}
	}
}