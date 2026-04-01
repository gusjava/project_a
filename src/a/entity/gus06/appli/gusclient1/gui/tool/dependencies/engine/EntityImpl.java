package a.entity.gus06.appli.gusclient1.gui.tool.dependencies.engine;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, V, R, E, P {

	public String creationDate() {return "20150822";}
	

	private Service getListing;
	private Service check;

	private List list;
	private String entityName;

	private int size;
	private String line;
	

	public EntityImpl() throws Exception
	{
		getListing = Outside.service(this,"gus06.entitydev.listing1");
		check = Outside.service(this,"gus06.entitydev.check.dependencies");
	}
	
	
	
	public void e() throws Exception
	{next();}
	
	
	
	public void p(Object obj) throws Exception
	{
		String rule = (String) obj;
		if(rule.equals("init")) {init();return;}
		throw new Exception("Unknown rule: "+rule);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("size")) return ""+size;
		if(key.equals("line")) return line;
		
		if(key.equals("keys")) return new String[]{"size","line"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private void init() throws Exception
	{
		if(entityName==null)
		{
			line = "EntityName has not been initialized";
			size = -1;
			return;
		}
		
		list = (List) getListing.g();
		size = list.size();
		line = null;
	}




	private void next() throws Exception
	{
		String next = (String) list.get(0);
		list.remove(0);
		
		boolean found = check.f(new String[]{next,entityName});
		line = found?next:null;
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("entityName")) {entityName = (String) obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
}
