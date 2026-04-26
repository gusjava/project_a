package a.entity.gus06.file.properties.perform.apply.script1;

import java.io.File;
import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150925";}
	
	public static final String DEFAULT_ = "DEFAULT";


	private Service readFile;
	private Service writeFile;
	
	private Service buildKeywords;
	private Service buildRules;
	private Service buildOther;
	private Service handleRule;
	
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.prop.read");
		writeFile = Outside.service(this,"gus06.file.write.properties");
		
		buildKeywords = Outside.service(this,"gus06.file.properties.perform.apply.script1.build.keywords");
		buildRules = Outside.service(this,"gus06.file.properties.perform.apply.script1.build.rules");
		buildOther = Outside.service(this,"gus06.file.properties.perform.apply.script1.build.other");
		handleRule = Outside.service(this,"gus06.file.properties.perform.apply.script1.handle.rule");
	}


	

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String script = (String) o[1];
		
		if(script.trim().equals("")) return;
		
		Map prop = (Map) readFile.t(file);
		
		Map rules = buildRules(script);
		String rule0 = buildDefaultRule(rules);
		Map other = buildOther(prop,rules);
		Map keywords = buildKeywords(file,prop,rules,other);
		
		
		Properties prop1 = new Properties();
		
		Iterator it = rules.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String rule = (String) rules.get(key);
			handleRule(key,rule,prop1,prop,keywords);
		}
		
		if(rule0!=null)
		{
			it = other.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				handleRule(key,rule0,prop1,prop,keywords);
			}
		}
		
		writeFile.p(new Object[]{file,prop1});
	}
	
	
	
	private Map buildRules(String script) throws Exception
	{return (Map) buildRules.t(script);}
	
	
	private Map buildOther(Map prop, Map rules) throws Exception
	{return (Map) buildOther.t(new Object[]{prop,rules});}
	
	
	private Map buildKeywords(File file, Map prop, Map rules, Map other) throws Exception
	{return (Map) buildKeywords.t(new Object[]{file,prop,rules,other});}
	
	
	private void handleRule(String key, String rule, Map prop1, Map prop, Map keywords) throws Exception
	{handleRule.p(new Object[]{key,rule,prop1,prop,keywords});}
	
	
	
	
	private String buildDefaultRule(Map rules)
	{
		if(!rules.containsKey(DEFAULT_)) return null;
		return (String) rules.remove(DEFAULT_);
	}
}
