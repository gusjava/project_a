package a.entity.gus06.appli.gusclient1.tool.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140814";}

	public static final String KEY = "tool.list";

	private Map prop;
	private List list;
	
	
	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"prop");
	}
	
	
	public Object g() throws Exception
	{
		if(list==null) init();
		return list;
	}
	
	
	
	private void init()
	{
		list = new ArrayList();
		if(!prop.containsKey(KEY)) return;
		String value = (String) prop.get(KEY);
		String[] n = value.split(";");
		for(String s:n) list.add(s);
	}
}
