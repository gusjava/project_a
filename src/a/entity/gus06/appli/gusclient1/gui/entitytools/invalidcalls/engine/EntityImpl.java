package a.entity.gus06.appli.gusclient1.gui.entitytools.invalidcalls.engine;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;

public class EntityImpl implements Entity, R, E, P {

	public String creationDate() {return "20150823";}
	

	private Service getListing;
	private Service retrieve;

	private List list;
	private List entities;

	private int size;
	private String line;
	

	public EntityImpl() throws Exception
	{
		getListing = Outside.service(this,"gus06.entitydev.listing1");
		retrieve = Outside.service(this,"gus06.entitydev.retrieve.dependencies1");
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
		entities = (List) getListing.g();
		
		list = new ArrayList(entities);
		size = list.size();
		line = null;
	}




	private void next() throws Exception
	{
		String next = (String) list.get(0);
		list.remove(0);
		
		StringBuffer b = new StringBuffer();
		Set set = (Set) retrieve.t(next);
		
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String call = (String) it.next();
			if(!entities.contains(call)) b.append(next+"@"+call+"\n");
		}
		line = b.toString();
	}
}
