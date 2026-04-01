package a.entity.gus06.file.string.dico.build.rowmap.col0.strict;

import a.framework.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161224";}
	
	public static final String DELIM = "\t";
	

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
			{
				String[] row = line.split(DELIM,-1);
				if(row.length>0)
				{
					if(map.containsKey(row[0]))
						throw new Exception("Key found many times: "+row[0]);
					map.put(row[0],row);
				}
			}
		}
		finally {fis.close();}
		
		return map;
	}
	
	
	
	private Charset charset(File file) throws Exception
	{
		Charset charset = (Charset) findCharset.t(file);
		return charset!=null?charset:Charset.defaultCharset();
	}
}
