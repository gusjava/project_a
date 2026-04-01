package a.entity.gus06.file.read.string.map.tn.count.autodetect;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201026";}
	
	
	private Service findCharset;
	
	public EntityImpl() throws Exception
	{
		findCharset = Outside.service(this,"gus06.file.string.info.charset");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		Map map = new HashMap();
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis,charset(file));
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
	
	
	
	private Charset charset(File file) throws Exception
	{
		Charset charset = (Charset) findCharset.t(file);
		return charset!=null?charset:Charset.defaultCharset();
	}
}
