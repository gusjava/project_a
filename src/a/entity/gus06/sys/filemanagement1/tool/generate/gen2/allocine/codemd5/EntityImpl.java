package a.entity.gus06.sys.filemanagement1.tool.generate.gen2.allocine.codemd5;

import a.framework.*;
import java.util.Map;
import java.io.PrintStream;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201119";}


	private Service emptyDir;
	private Service readText;
	private Service getName0;
	private Service setToString;
	private Service writeText;
	
	public EntityImpl() throws Exception
	{
		emptyDir = Outside.service(this,"gus06.dir.perform.empty.andcreate");
		readText = Outside.service(this,"gus.x.file.string.read.v1");
		getName0 = Outside.service(this,"gus.x.file.getname0");
		setToString = Outside.service(this,"gus06.tostring.set");
		writeText = Outside.service(this,"gus.x.file.string.write");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dirGen = (File) o[0];
		File[] ff = (File[]) o[1];
		String dirName = (String) o[2];
		
		File dir = new File(dirGen,dirName);
		emptyDir.p(dir);
		
		Map map = new HashMap();
		for(File f : ff)
		{
			String md5 = (String) getName0.t(f);
			String code = (String) readText.t(f);
			
			add(map,code,md5);
		}
		writeToDir(dir,map);
		return map;
	}
	
	
	
	private void add(Map map, String key, String value)
	{
		if(!map.containsKey(key))
		map.put(key,new HashSet());
		((Set) map.get(key)).add(value);
	}
	
	private void writeToDir(File dir, Map map) throws Exception
	{
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			Set set = (Set) map.get(key);
			String str = (String) setToString.t(set);
			
			File f = new File(dir,key+".txt");
			writeText(f,str);
		}
	}
	
	private void writeText(File f, String s)
	{
		try{writeText.p(new Object[]{f,s});}
		catch(Exception e)
		{
			Exception e1 = new Exception("Failed to write text inside file: "+f,e);
			Outside.err(this,"writeText(File,String)",e1);
		}
	}
}