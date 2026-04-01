package a.entity.gus06.sys.filemanagement1.scan.previous.read;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191128";}

	public static final String CHARSET = "UTF-8";
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		Map map = new HashMap();
		
		if(file==null || !file.isFile()) return map;
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis,CHARSET);
		BufferedReader br = new BufferedReader(isr);
		
		try
		{
			String line = null;
			while((line = br.readLine())!=null)
			if(!line.equals(""))
			{
				String[] row = line.split("\t",-1);
				if(row.length!=6) throw new Exception("Invalid line: "+line);
				
				String location = row[0];
				String name = row[1];
				
				map.put(location+"\t"+name,row);
			}
		}
		finally {fis.close();}
		return map;
	}
}
