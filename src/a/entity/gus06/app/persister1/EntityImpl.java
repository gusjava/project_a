package a.entity.gus06.app.persister1;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, V, R, G, F {

	public String creationDate() {return "20140912";}


	private Service builder;
	private Object holder;


	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.app.persister1.builder");
		File dir = (File) Outside.resource(this,"defaultdir");
		holder = builder.t(dir);
	}
	
	public Object r(String key) throws Exception
	{return ((R) holder).r(key);}
	
	public void v(String key, Object obj) throws Exception
	{((V) holder).v(key,obj);}
	
	public Object g() throws Exception
	{return ((G) holder).g();}
	
	public boolean f(Object obj) throws Exception
	{return ((F) holder).f(obj);}
}
