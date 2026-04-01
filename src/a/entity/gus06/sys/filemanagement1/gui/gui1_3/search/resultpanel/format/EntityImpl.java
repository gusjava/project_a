package a.entity.gus06.sys.filemanagement1.gui.gui1_3.search.resultpanel.format;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201124";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		List list = (List) obj;
		List list1 = new ArrayList();
		
		Map md5Map = new HashMap();
		int nb = list.size();
		for(int i=0;i<nb;i++)
		{
			String[] row = (String[]) list.get(i);
			
			String rootName = row[0];
			String location = row[1];
			String fileName = row[2];
			String size = row[3];
			String modified = row[4];
			String md5 = row[5];
			String mime = row[6];
			
			String md5I = findMd5I(md5Map,md5);
			
			list1.add(new String[]{rootName,location,fileName,size,md5I});
			
		}
		return list1;
	}
	
	
	private String findMd5I(Map map, String md5)
	{
		if(!map.containsKey(md5)) map.put(md5,map.size()+1);
		return ""+map.get(md5);
	}
}