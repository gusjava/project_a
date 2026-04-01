package a.entity.gus06.file.read.string.map.tn.count;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201026";}

	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		Map map = new HashMap();
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		try
		{
			String line = null;
			while((line = br.readLine())!=null)
			handleLine(line,map);
		}
		finally {fis.close();}
		
		return map;
	}
	
	
	private void handleLine(String line, Map map) throws Exception
	{
		String[] n = line.split("\t");
		if(n.length!=2) throw new Exception("Invalid line: "+line);
		
		String key = n[0];
		if(map.containsKey(key)) throw new Exception("Key found many times: "+key);
		
		Integer count = Integer.valueOf(n[1]);
		map.put(key,count);
	}
}
