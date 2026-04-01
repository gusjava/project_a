package a.entity.gus06.string.html.form.parse1;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191027";}

	public static final Pattern P = Pattern.compile("(?si)<form( [^>]*)?>(.*?)</form>");

	
	private Service buildParams;
	private Service extractInputs;

	public EntityImpl() throws Exception
	{
		buildParams = Outside.service(this,"gus06.sys.xhtmlparser1.analyze2.buildparams");
		extractInputs = Outside.service(this,"gus06.string.extract.html.tag.type.input.a");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String text = (String) obj;
		Matcher m = P.matcher(text);
		
		List list = new ArrayList();
		while(m.find())
		{
			Map tagParams = (Map) buildParams.t(m.group(1));
			String tagContent = m.group(2);
			
			Map tagMap = new HashMap();
			tagMap.put("params",tagParams);
			tagMap.put("content",tagContent);
			
			List inputs1 = (List) extractInputs.t(tagContent);
			List inputs = new ArrayList();
			Map inputMap = new HashMap();
			
			for(int i=0;i<inputs1.size();i++)
			{
				Map input = (Map) buildParams.t(inputs1.get(i));
				
				String name = get0(input,"name");
				String value = get0(input,"value","");
				
				inputs.add(input);
				
				if(name!=null && !isDisabled(input))
				inputMap.put(name,value);
			}
			
			
			Map map = new HashMap();
			
			map.put("action",get1(tagParams,"action"));
			map.put("method",get1(tagParams,"method"));
			map.put("tag",tagMap);
			map.put("inputs",inputs);
			map.put("params",inputMap);
			
			list.add(map);
		}
		return list;
	}
	
	
	
	
	private boolean isDisabled(Map input)
	{
		String disabled = get0(input,"disabled");
		if(disabled!=null)
		{
			if(disabled.equals("disabled")) return true;
		}
		String type = get0(input,"type");
		if(type!=null)
		{
			 if(type.equals("button")) return true;
			 if(type.equals("submit")) return true;
		}
		return false;
	}
	
	
	private String get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return (String) map.get(key);
	}
	
	private String get0(Map map, String key, String defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return (String) map.get(key);
	}
	
	private String get0(Map map, String key)
	{
		return get0(map,key,null);
	}
}
