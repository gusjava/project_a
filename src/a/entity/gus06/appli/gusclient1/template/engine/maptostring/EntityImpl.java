package a.entity.gus06.appli.gusclient1.template.engine.maptostring;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140905";}

	public static final String KEY_TEMPLATE = "template";
	

	private Service findTemplate;


	public EntityImpl() throws Exception
	{
		findTemplate = Outside.service(this,"gus06.appli.gusclient1.template.find");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		String id = get(map,KEY_TEMPLATE);
		
		String template = (String) findTemplate.r(id);
		if(template==null) throw new Exception("Template not found: "+id);
		
		String[] lines = template.split("\n",-1);
		if(lines.length<2) throw new Exception("Invalid template for id: "+id);
		
		String firstLine = lines[0];
		String[] keys = firstLine.split(";");
		
		StringBuffer b = new StringBuffer();
		for(int i=1;i<lines.length;i++)
		{
			String line = buildLine(lines[i],keys,map);
			b.append(line+"\n");
		}
		return b.toString();
	}
	
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return (String) map.get(key);
	}
	
	
	
	private String buildLine(String input, String[] keys, Map map) throws Exception
	{
		for(String key:keys) input = input.replace("<"+key+">",get(map,key));
		return input;
	}
}
