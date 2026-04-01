package a.entity.gus06.sys.filetool.ext.library1.perform.paste2.files;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201231";}

	public static final String STRUCT = "struct";
	public static final String CONTENT = "content";
	
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		List keys0 = (List) o[1];
		List list = (List) o[2];
		
		if(list.isEmpty()) return false;
		
		String struct = get0(map,STRUCT);
		if(struct==null) struct = "";
		
		int nb = Math.min(keys0.size(),list.size());
		for(int i=0;i<nb;i++)
		{
			String key = (String) keys0.get(i);
			File file = (File) list.get(i);
			
			String path = file.getAbsolutePath();
			map.put(CONTENT+"."+key,path);
		}
		return true;
	}
	
	private String get0(Map map, String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}