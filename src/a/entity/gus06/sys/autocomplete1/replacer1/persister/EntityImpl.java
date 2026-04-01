package a.entity.gus06.sys.autocomplete1.replacer1.persister;

import a.framework.*;
import java.util.Set;

public class EntityImpl implements Entity, G, R, V {

	public String creationDate() {return "20160424";}


	private Service persistString;
	private Service persistSet;


	public EntityImpl() throws Exception
	{
		persistString = Outside.service(this,"gus06.app.persister1");
		persistSet = Outside.service(this,"gus06.app.persister1.data.set");
	}
	
	
	
	public Object g() throws Exception
	{
		return getSet("all");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String rule = o[0];
		String options = o[1];
		
		if(rule==null || options==null) return;
		
		recordKey(key);
		setString("rule_"+key,rule);
		setString("options_"+key,options);
	}
	
	
	public Object r(String key) throws Exception
	{
		String rule = getString("rule_"+key);
		String options = getString("options_"+key);
		
		if(rule==null || options==null) return null;
		return new String[]{rule,options};
	}
	
	
	
	
	private void setString(String key, String value) throws Exception
	{persistString.v(getClass().getName()+"_"+key,value);}
	
	private String getString(String key) throws Exception
	{return (String) persistString.r(getClass().getName()+"_"+key);}
	
	
	
	
	private void setSet(String key, Set value) throws Exception
	{persistSet.v(getClass().getName()+"_"+key,value);}
	
	private Set getSet(String key) throws Exception
	{return (Set) persistSet.r(getClass().getName()+"_"+key);}
	
	
	
	
	private void recordKey(String key) throws Exception
	{
		if(key.startsWith("input_")) key = key.substring(6);
		else if(key.startsWith("output_")) key = key.substring(7);
		
		Set all = getSet("all");
		all.add(key);
		setSet("all",all);
	}
}
