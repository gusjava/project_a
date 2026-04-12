package a.entity.gus06.ling.language.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140719";}

	public static final String KEY = "language.list";

	private Map props;
	private List list;
	
	
	public EntityImpl() throws Exception
	{
		props = (Map) Outside.resource(this,"props");
	}
	
	
	public Object g() throws Exception
	{
		if(list==null) init();
		return list;
	}
	
	
	
	private void init() throws Exception
	{
		if(!props.containsKey(KEY))
			throw new Exception("Property not found: "+KEY);
		String value = (String) props.get(KEY);
		
		list = new ArrayList();
		String[] n = value.split(";");
		for(String s:n) list.add(s);
	}
}
