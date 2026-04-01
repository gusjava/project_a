package a.entity.gus06.sys.xhtml1.ids.findall.asmap;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220908";}
	
	public static final String KEY_FILE = "file";
	public static final String KEY_CONTENT = "content";
	public static final String KEY_LOCATION = "location";
	public static final String KEY_CHILDREN = "children";


	private Service extractAll;

	public EntityImpl() throws Exception
	{
		extractAll = Outside.service(this,"gus06.sys.xhtml1.extract.id");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map data = (Map) obj;
		if(data==null) return null;
		
		Map map = new HashMap();
		handleData(map,data);
		return map;
	}
	
	
	private void handleData(Map map, Map data) throws Exception
	{
		String content = (String) data.get(KEY_CONTENT);
		String location = (String) data.get(KEY_LOCATION);
		List children = (List) data.get(KEY_CHILDREN);
		File file = (File) data.get(KEY_FILE);
		
		List ids = (List) extractAll.t(content);
		for(int i=0;i<ids.size();i++)
		{
			String id = (String) ids.get(i);
			addTo(map,id,file,location);
		}
		if(children!=null)
		for(int i=0;i<children.size();i++)
		{
			Map child = (Map) children.get(i);
			handleData(map,child);
		}
	}
	
	
	private void addTo(Map m, String id, File file, String location)
	{
		if(!m.containsKey(id)) m.put(id,new ArrayList());
		((List) m.get(id)).add(new Object[]{file,location,id});
	}
}