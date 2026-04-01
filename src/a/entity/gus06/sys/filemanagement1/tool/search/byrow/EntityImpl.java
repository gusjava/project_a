package a.entity.gus06.sys.filemanagement1.tool.search.byrow;

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

	public String creationDate() {return "20201124";}

	public static final String CHARSET = "UTF-8";


	private Service rowToMap;
	
	public EntityImpl() throws Exception
	{
		rowToMap = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.row1.tomap");
	}

	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map fileMap = (Map) o[0];
		F filter = (F) o[1];
						
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
						String[] row1 = new String[]{rootName,row[0],row[1],row[2],row[3],row[4],row[5]};
						
						Map m = (Map) rowToMap.t(row1);
						if(filter.f(m))
						{
							results.add(row1);
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