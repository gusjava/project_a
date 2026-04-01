package a.entity.gus06.appli.gusclient1.gui.entitytools.unusedentities.engine;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashSet;

public class EntityImpl implements Entity, R, E, P {

	public String creationDate() {return "20150823";}
	

	private Service getListing;
	private Service retrieve;

	private List entities;
	private List list1;
	private List list2;
	private Set used;

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
		
		list1 = new ArrayList(entities);
		list2 = new ArrayList(entities);
		used = new HashSet();
		
		size = entities.size()*2;
		line = null;
	}




	private void next() throws Exception
	{
		if(!list1.isEmpty()) handleList1();
		else handleList2();
	}
	
	
	
	
	private void handleList1() throws Exception
	{
		String s = (String) list1.get(0);
		list1.remove(0);
		
		Set set = (Set) retrieve.t(s);
		used.addAll(set);
		line = null;
	}
	
	
	private void handleList2() throws Exception
	{
		String s = (String) list2.get(0);
		list2.remove(0);
		
		if(!used.contains(s)) line = s;
		else line = null;
	}
}
