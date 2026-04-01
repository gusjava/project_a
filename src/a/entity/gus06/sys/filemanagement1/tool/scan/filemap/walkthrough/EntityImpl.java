package a.entity.gus06.sys.filemanagement1.tool.scan.filemap.walkthrough;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.io.PrintStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201008";}

	public static final String CHARSET = "UTF-8";
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map fileMap = (Map) o[0];
		P handler = (P) o[1];
		
		Iterator it = fileMap.keySet().iterator();
		while(it.hasNext())
		{
			String rootName = (String) it.next();
			File file = (File) fileMap.get(rootName);
			if(file.isFile())
			{
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
						if(row.length!=6) throw new Exception("Invalid line: ["+line+"]: parts nb="+row.length);
						
						String location = row[0];
						String fileName = row[1];
						String size = row[2];
						String modified = row[3];
						String md5 = row[4];
						String mimeType = row[5];
						
						String[] data = new String[]{rootName,location,fileName,size,modified,md5,mimeType};
						handler.p(data);
					}
				}
				catch(Exception e)
				{
					String message = "Analyze failed for file: "+file;
					throw new Exception(message,e);
				}
				finally {fis.close();}
			}
		}
	}
}
