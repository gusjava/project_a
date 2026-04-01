package a.entity.gus06.sys.script1.analyze2;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150829";}
	
	public static final String K_VALUE = "value";
	public static final String K_CONTENT = "content";
	public static final String K_UNTIL = "until";
	public static final String K_PARENT = "parent";
	public static final String K_INDEX = "index";

	



	private Service buildTagRoot;
	private Service prepare;

	public EntityImpl() throws Exception
	{
		buildTagRoot = Outside.service(this,"gus06.sys.script1.analyze2.buildtag.root");
		prepare = Outside.service(this,"gus06.sys.script1.analyze2.preparetag");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		List list = new ArrayList((List) obj);
		
		Map root = (Map) buildTagRoot.g();
		handleTag(root,list);
		return root;
	}
	
	
	
	
	private void handleTag(Map tag, List list) throws Exception
	{
		prepare.p(tag);
		String until = get(tag,K_UNTIL);
		if(until!=null) fillTag(tag,list,until);
	}
	
	
	
	private void fillTag(Map tag, List list, String until) throws Exception
	{
		List content = new ArrayList();
		tag.put(K_CONTENT,content);
		
		boolean endReached = false;
		while(!list.isEmpty() && !endReached)
		{
			Map next = nextTag(list);
			content.add(next);
			next.put(K_PARENT,tag);
			next.put(K_INDEX,""+(content.size()-1));
			handleTag(next,list);
			
			endReached = isEnd(next,until);
		}
	}
	
	
	
	
	
	private boolean isEnd(Map tag, String until)
	{
		String value = get(tag,K_VALUE);
		return value!=null && value.equals(until);
	}
	
	
	private Map nextTag(List list)
	{return (Map) list.remove(0);}
	
	
	private String get(Map map, String key)
	{return map.containsKey(key)?(String) map.get(key):null;}
}
