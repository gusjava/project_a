package a.entity.gus06.java.searchclass.fromrt;

import a.framework.*;
import java.io.File;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140728";}

	

	private Service findRt;
	private Service fromRt2;

	public EntityImpl() throws Exception
	{
		findRt = Outside.service(this,"gus06.java.home.rtjar");
		fromRt2 = Outside.service(this,"gus06.java.searchclass.fromrt2");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String query = ((String) obj).toLowerCase();
		File rt = (File) findRt.g();
		
		if(rt!=null && rt.isFile()) return searchFromRT(rt,query);
		return fromRt2.t(query);
	}
	
	
	
	private List searchFromRT(File rt, String query) throws Exception
	{
		JarFile jar = null;
		List list = new ArrayList();
		
		try
		{
			jar = new JarFile(rt,true,JarFile.OPEN_READ);
			Enumeration en = jar.entries();
			while(en.hasMoreElements())
			{
				JarEntry entry = (JarEntry)en.nextElement();
				if(!entry.isDirectory())
				{
					String entryName = entry.getName();
					String classPath = toClasspath(entryName);
					if(match(classPath,query)) list.add(classPath);
				}
			}
		}
		finally
		{
			if(jar!=null) jar.close();
		}
		return list;
	}
	
		

	public static String JARPATHSEP = "/";

	private String toClasspath(String entryName)
	{return entryName.substring(0,entryName.length()-6).replace(JARPATHSEP,".");}
	
	
	
	
	private boolean match(String classPath, String query)
	{
		if(classPath.contains("$")) return false;
		
		String p = classPath.toLowerCase();
		if(query.contains(".")) return p.equals(query);
		
		String[] n = p.split("\\.");
		String last = n[n.length-1];
		return last.startsWith(query);
	}
}
