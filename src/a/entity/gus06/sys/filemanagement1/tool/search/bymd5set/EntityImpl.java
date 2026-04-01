package a.entity.gus06.sys.filemanagement1.tool.search.bymd5set;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201108";}

	public static final String CHARSET = "UTF-8";

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map fileMap = (Map) o[0];
		Set md5Set = (Set) o[1];
		
		List results = new ArrayList();
		List rootNames = new ArrayList(fileMap.keySet());
		
		Collections.sort(rootNames);
		
		for(int i=0;i<rootNames.size();i++)
		{
			String rootName = (String) rootNames.get(i);
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
						
						if(!row[2].equals(""))
						{
							String location = row[0];
							String fileName = row[1];
							String size = row[2];
							String modified = row[3];
							String md5 = row[4];
							String mimeType = row[5];
							
							if(md5Set.contains(md5))
							{
								String[] data = new String[]{rootName,location,fileName,size,modified,md5,mimeType};
								results.add(data);
							}
						}
					}
				}
				catch(Exception e)
				{
					String message = "Search failed for file: "+file;
					throw new Exception(message,e);
				}
				finally {fis.close();}
			}
		}
		
		return results;
	}
}
