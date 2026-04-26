package a.entity.gus06.sys.filemanagement1.tool.generate.gen2.allocine.full;

import a.framework.*;
import java.util.Map;
import java.io.PrintStream;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210201";}


	private Service emptyDir;
	private Service getName0;
	private Service readProp;
	private Service setToString;
	private Service writeText;
	
	public EntityImpl() throws Exception
	{
		emptyDir = Outside.service(this,"gus06.dir.perform.empty.andcreate");
		getName0 = Outside.service(this,"gus06.file.getname0");
		readProp = Outside.service(this,"gus06.file.read.properties");
		setToString = Outside.service(this,"gus06.tostring.set");
		writeText = Outside.service(this,"gus.x.file.string.write");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
		
		File dirGen = (File) o[0];
		File[] ff = (File[]) o[1];
		Set codes = (Set) o[2];
		String dirName = (String) o[3];
		String key = (String) o[4];
		
		File dir = new File(dirGen,dirName);
		File dirCount = new File(dirGen,"counts");
		File fileCount = new File(dirCount,dirName+".txt");
		
		emptyDir.p(dir);
		dirCount.mkdirs();
		
		Map map = new HashMap();
		for(File f : ff)
		{
			String code = (String) getName0.t(f);
			if(codes.contains(code))
			{
				Map prop = (Map) readProp.t(f);
				handleProp(prop, key, code, map);
			}
		}
		
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		
		writeToDir(dir,keys,map);
		writeCount(fileCount,keys,map);
	}
	
	
	private void handleProp(Map prop, String key, String code, Map map) throws Exception
	{
		if(!prop.containsKey(key)) return;
		
		String value = (String) prop.get(key);
		value = formatValue(value);
		add(map,value,code);
	}
	
	
	
	
	private void add(Map map, String key, String value)
	{
		if(!map.containsKey(key))
		map.put(key,new HashSet());
		((Set) map.get(key)).add(value);
	}
	
	
	private void writeToDir(File dir, List keys, Map map) throws Exception
	{
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			Set set = (Set) map.get(key);
			String str = (String) setToString.t(set);
			
			File f = new File(dir,i+".txt");
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
	
	
	
	private void writeCount(File file, List keys, Map map) throws Exception
	{
		PrintStream p = new PrintStream(file,"UTF-8");
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			Set set = (Set) map.get(key);
			int count = set.size();
			
			if(key.contains("\t")) throw new Exception("Invalid key for count file: "+key);
			p.println(key+"\t"+count);
		}
		p.close();
	}
	
	
	private String formatValue(String value) throws Exception
	{
		return value.trim().replaceAll("[\n\t ]+"," ");
	}
}