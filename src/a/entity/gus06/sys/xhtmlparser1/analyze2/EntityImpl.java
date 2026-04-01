package a.entity.gus06.sys.xhtmlparser1.analyze2;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170131";}
	
	public static final boolean LENIENT = true;
	
	public static final String K_VALUE = "value";
	public static final String K_NAME = "name";
	public static final String K_CONTENT = "content";
	public static final String K_CLOSINGTAG = "closingtag";
	public static final String K_UNTIL = "until";
	public static final String K_INDEX = "index";
	
	
	private Service buildTagRoot;
	private Service prepare;

	public EntityImpl() throws Exception
	{
		buildTagRoot = Outside.service(this,"gus06.sys.xhtmlparser1.analyze2.buildtag.root");
		prepare = Outside.service(this,"gus06.sys.xhtmlparser1.analyze2.preparetag");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		List list = new ArrayList((List) obj);
		
		Map root = (Map) buildTagRoot.g();
		fillTag(root,list,null);
		return root;
	}
	
	
	
	
	private void handleTag(Map tag, List list) throws Exception
	{
		prepare.p(tag);
		String until = get(tag,K_UNTIL);
		if(until!=null) fillTag(tag,list,until);
		else if(LENIENT) handleTagLenient(tag,list);
	}
	
	
	
	private void handleTagLenient(Map tag, List list) throws Exception
	{
		String value = (String) tag.get(K_VALUE);
		if(value.endsWith("/")) return;
		
		String name = (String) tag.get(K_NAME);
		if(!hasClosingTag(list,name)) return;
		
		fillTag(tag,list,"/"+name);
	}
	
	
	
	private void fillTag(Map tag, List list, String until) throws Exception
	{
		List content = new ArrayList();
		tag.put(K_CONTENT,content);
		
		while(!list.isEmpty())
		{
			Map next = nextTag(list);
			if(isEnd(next,until))
			{
				tag.put(K_CLOSINGTAG,next);
				return;
			}
			content.add(next);
			next.put(K_INDEX,content.size()-1);
			handleTag(next,list);
		}
	}
	
	
	
	
	
	private boolean isEnd(Map tag, String until)
	{
		String value = get(tag,K_VALUE);
		return until!=null && value!=null && value.equals(until);
	}
	
	
	private Map nextTag(List list)
	{return (Map) list.remove(0);}
	
	
	private String get(Map map, String key)
	{return map.containsKey(key)?(String) map.get(key):null;}
	
	
	
	private boolean hasClosingTag(List list, String name)
	{
		String closingValue = "/"+name;
		for(int i=0;i<list.size();i++)
		{
			Map tag = (Map) list.get(i);
			if(get(tag,K_VALUE).equals(closingValue)) return true;
		}
		return false;
	}
}