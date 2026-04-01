package a.entity.gus06.sys.script1.access.tag.content1;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150902";}
	
	public static final String K_CONTENT = "content";
	public static final String K_VALUE = "value";
	public static final String V_END = "end";

	
	
	public Object t(Object obj) throws Exception
	{
		Map tag = (Map) obj;
			
		List content = (List) get(tag,K_CONTENT);
		if(content==null) throw new Exception("Content not found inside tag ["+tag+"]");
		
		if(!content.isEmpty())
		{
			int lastIndex = content.size()-1;
			Map last = (Map) content.get(lastIndex);
			if(isEnd(last)) content.remove(lastIndex);
		}
		return content;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)?map.get(key):null;}
	
	
	
	private boolean isEnd(Map tag)
	{
		Object value = get(tag,K_VALUE);
		return value!=null && value.equals(V_END);
	}
}
