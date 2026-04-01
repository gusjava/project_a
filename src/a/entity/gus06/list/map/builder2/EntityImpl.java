package a.entity.gus06.list.map.builder2;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190323";}


	public EntityImpl() throws Exception
	{}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[][] data = (Object[][]) obj;
		List list = new ArrayList();
		
		if(data.length==0) return list;
		
		Object[] header = data[0];
		for(int i=1;i<data.length;i++)
		list.add(buildMap(header,data[i]));
		
		return list;
	}
	
	
	private Map buildMap(Object[] header, Object[] row)
	{
		Map map = new HashMap();
		for(int i=0;i<header.length;i++)
		map.put(header[i],row[i]);
		return map;
	}
}
