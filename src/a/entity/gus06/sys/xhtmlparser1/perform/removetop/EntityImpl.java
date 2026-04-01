package a.entity.gus06.sys.xhtmlparser1.perform.removetop;

import a.framework.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170313";}

	public static final String K_CONTENT = "content";
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map root = (Map) obj;
		if(root==null) return "";
		
		List content = (List) get(root,K_CONTENT);
		if(content==null) return "";
		
		List newContent = new ArrayList();
		for(int i=0;i<content.size();i++)
		{
			Map child = (Map) content.get(i);
			List c = (List) get(child,K_CONTENT);
			
			if(c!=null) newContent.addAll(c);
			else newContent.add(child);
		}
		
		content.clear();
		content.addAll(newContent);
		
		return root;
	}
	
	
	private Object get(Map map, String key)
	{return map.containsKey(key)? map.get(key):null;}
}
