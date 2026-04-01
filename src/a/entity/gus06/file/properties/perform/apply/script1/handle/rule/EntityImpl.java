package a.entity.gus06.file.properties.perform.apply.script1.handle.rule;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150925";}


	private Service applyTags;


	public EntityImpl() throws Exception
	{
		applyTags = Outside.service(this,"gus06.file.properties.perform.apply.script1.handle.rule.tags");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
		
		String key = (String) o[0];
		String rule = (String) o[1];
		Map prop1 = (Map) o[2];
		Map prop = (Map) o[3];
		Map keywords = (Map) o[4];
		
		
		if(rule.equals("-"))
		{
			return;
		}
		else if(rule.equals("+"))
		{
			transfert(prop,prop1,key);
			return;
		}
		else if(rule.startsWith(">"))
		{
			boolean ok = transfert(prop,prop1,key);
			if(ok) return;
		}
		else if(rule.startsWith("<"))
		{
			if(!prop.containsKey(key)) return;
		}
		else if(rule.startsWith("!"))
		{
			
		}
		else throw new Exception("Invalid rule: "+rule);
		
		
		
		Map values = prepareValues(key,prop,keywords);
		
		rule = rule.substring(1);
		rule = applyTags(rule,values);
		
		prop1.put(key,rule);
	}
	
	
	
	
	private Map prepareValues(String key, Map prop, Map keywords)
	{
		String currentKey = key;
		String currentValue = get(prop,key);
		
		keywords.put("$fieldname",currentKey);
		keywords.put("$fieldvalue",currentValue);
		keywords.put("$",currentValue);
		
		Map values = new HashMap();
		values.putAll(prop);
		values.putAll(keywords);
		
		return values;
	}
	
	
	
	private String get(Map m, String key)
	{
		if(!m.containsKey(key)) return "";
		return (String) m.get(key);
	}
	
	private boolean transfert(Map m1, Map m2, String key)
	{
		if(!m1.containsKey(key)) return false;
		m2.put(key,m1.get(key));
		return true;
	}
	
	
	private String applyTags(String rule, Map values) throws Exception
	{return (String) applyTags.t(new Object[]{rule,values});}
}
