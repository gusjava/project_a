package a.entity.gus06.appli.gusclient1.project.config.entities.listing0.f1;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150312";}

	public static final Pattern P = Pattern.compile("([a-z][a-z0-9_]*)(\\.[a-z][a-z0-9_]*)*");
	
	
	private Service loadProp;
	private Service loadMapping;

	public EntityImpl() throws Exception
	{
		loadProp = Outside.service(this,"gus06.appli.gusclient1.project.config.load2.prop");
		loadMapping = Outside.service(this,"gus06.appli.gusclient1.project.config.load2.mapping");
	}
	
	
	
	
	public Object g() throws Exception
	{
		Map prop = (Map) loadProp.g();
		Map mapping = (Map) loadMapping.g();
		
		Set set = new HashSet();
		
		Iterator it1 = prop.keySet().iterator();
		while(it1.hasNext())
		{
			String key = (String) it1.next();
			String value = (String) prop.get(key);
			set.addAll(extract(value));
		}
		
		Iterator it2 = mapping.keySet().iterator();
		while(it2.hasNext())
		{
			String key = (String) it2.next();
			String value = (String) mapping.get(key);
			set.addAll(extract(value));
		}
		
		return new Filter(set);
	}
	
	
	
	
	private Set extract(String value)
	{
		Set set = new HashSet();
		Matcher m = P.matcher(value);
		while(m.find()) set.add(m.group());
		return set;
	}
	
	
	
	private class Filter implements F
	{
		private Set set;
		public Filter(Set set) {this.set = set;}
		
		public boolean f(Object obj) throws Exception
		{return set.contains(obj);}
	}
}
