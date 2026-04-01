package a.entity.gus06.list.groupby2.filesize;

import a.framework.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180310";}


	private Service putInList;
	private Service keepColMany;
	
	public EntityImpl() throws Exception
	{
		putInList = Outside.service(this,"gus06.map.put.inlist");
		keepColMany = Outside.service(this,"gus06.map.keepcol.many");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		List input = (List) obj;
		
		Map map = new HashMap();
		for(Object elem:input)
		{
			File file = (File) elem;
			Long key = Long.valueOf(file.length());
			putInList.p(new Object[]{map,key,file});
		}
		
		keepColMany.p(map);
		return map;
	}
}
