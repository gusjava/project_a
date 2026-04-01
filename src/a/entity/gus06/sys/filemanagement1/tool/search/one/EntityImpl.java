package a.entity.gus06.sys.filemanagement1.tool.search.one;

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

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201103";}

	public static final String CHARSET = "UTF-8";
	public static final int INFO_NUMBER = 7;
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map fileMap = (Map) o[0];
		F[] filters = (F[]) o[1];
		
		if(filters.length!=INFO_NUMBER) throw new Exception("Invalid line filters: parts nb="+filters.length);
						
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
						String line1 = rootName+'\t'+line;
						String[] data = line1.split("\t",-1);
						if(data.length!= INFO_NUMBER) throw new Exception("Invalid line: ["+line1+"]: parts nb="+data.length);
						
						if(!data[2].equals("")) // size is defined
						{
							if(filter(filters, data)) results.add(data);
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
	
	
	
	private boolean filter(F[] filters, String[] row) throws Exception
	{
		for(int i=0;i<row.length;i++)
		if(filters[i]!=null && filters[i].f(row[i])) return true;
		return false;
	}
}