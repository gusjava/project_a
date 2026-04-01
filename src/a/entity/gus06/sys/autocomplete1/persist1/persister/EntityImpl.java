package a.entity.gus06.sys.autocomplete1.persist1.persister;

import a.framework.*;

public class EntityImpl implements Entity, R, V {

	public String creationDate() {return "20160425";}


	private Service persister;


	public EntityImpl() throws Exception
	{
		persister = Outside.service(this,"gus06.app.persister1");
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{set(key,(String) obj);}
	
	public Object r(String key) throws Exception
	{return get(key);}
	
	
	
	private void set(String key, String value) throws Exception
	{persister.v(getClass().getName()+"_"+key,value);}
	
	private String get(String key) throws Exception
	{return (String) persister.r(getClass().getName()+"_"+key);}
}
