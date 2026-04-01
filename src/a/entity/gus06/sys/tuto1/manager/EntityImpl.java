package a.entity.gus06.sys.tuto1.manager;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl extends S1 implements Entity, F, R, V, G {

	public String creationDate() {return "20160701";}


	private Service inside;
	
	private File storeDir;
	private String name;
	private Map map;


	public EntityImpl() throws Exception
	{
		inside = Outside.service(this,"gus06.app.inside.tuto1");
		storeDir = (File) Outside.resource(this,"defaultdir");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		return true;
	}
	
	
	
	public Object g() throws Exception
	{return map;}
	
	
	
	public Object r(String key) throws Exception
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("load")) {load((String) obj);return;}
		if(key.equals("validate")) {validate((String) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void load(String name) throws Exception
	{
		this.name = name;
		map = (Map) inside.r(name);
	}
	
	
	private void validate(String name) throws Exception
	{
		
	}
}
