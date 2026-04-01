package a.entity.gus06.appli.gusclient1.gui.tool.textsearch.engine;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, V, R, E, P {

	public String creationDate() {return "20201028";}
	

	private Service getListing;
	private Service retrieveSrc;

	private List list;
	private String query;

	private int size;
	private String line;
	

	public EntityImpl() throws Exception
	{
		getListing = Outside.service(this,"gus06.entitydev.listing1");
		retrieveSrc = Outside.service(this,"gus06.entitydev.retrieve.srccode1");
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
		if(query==null)
		{
			line = "query has not been initialized";
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
		
		String src = (String) retrieveSrc.t(next);
		boolean found = src.contains(query);
		line = found?next:null;
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("query")) {query = (String) obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
}