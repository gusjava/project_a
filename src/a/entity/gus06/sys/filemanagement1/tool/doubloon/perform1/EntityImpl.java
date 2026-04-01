package a.entity.gus06.sys.filemanagement1.tool.doubloon.perform1;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191210";}

	public static final String CHARSET = "UTF-8";
	public static final String FAILED_MD5 = "###";

	
	
	public Object t(Object obj) throws Exception
	{
		Map fileMap = (Map) obj;
		
		Map md5_places = new HashMap();
		Map md5_size = new HashMap();
		Map md5_doubloon = new HashMap();
		
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
						
						if(!row[2].equals(""))
						{
							String location = row[0];
							String fileName = row[1];
							Long size = Long.valueOf(row[2]);
							String md5 = row[4];
						
							if(size>0 && !md5.equals(FAILED_MD5))
							{
								if(!md5_size.containsKey(md5))
									md5_size.put(md5,size);
								else
								{
									Long size_ = (Long) md5_size.get(md5);
									if(!size_.equals(size)) throw new Exception("Different sizes found ["+size+","+size_+"] for same md5 ["+md5+"]");
								}
								
								Set set = findSet(md5_places,md5);
								set.add(rootName+"\t"+location+"\t"+fileName);
							}
						}
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
		
		long totalLostSpace = 0;
		long deletableNb = 0;
		
		it = md5_places.keySet().iterator();
		while(it.hasNext())
		{
			String md5 = (String) it.next();
			Set set = (Set) md5_places.get(md5);
			int nb = set.size();
			
			if(nb>=2) 
			{
				long fileSize = (long) md5_size.get(md5);
				long nb1 = nb-1;
				long lostSpace = fileSize*nb1;
				
				totalLostSpace += lostSpace;
				deletableNb += nb1;
				
				Map doubloon = new HashMap();
				doubloon.put("fileSize",fileSize);
				doubloon.put("lostSpace",lostSpace);
				doubloon.put("number",nb);
				doubloon.put("places",set);
				
				md5_doubloon.put(md5,doubloon);
			}
		}
		
		
		Map output = new HashMap();
		
		output.put("doubloons",md5_doubloon);
		output.put("doubloonNb",md5_doubloon.size());
		output.put("deletableNb",deletableNb);
		output.put("total",totalLostSpace);
		
		return output;
	}
	
	
	
	
	private Set findSet(Map m, String key)
	{
		if(!m.containsKey(key)) m.put(key,new HashSet());
		return (Set) m.get(key);
	}
}
