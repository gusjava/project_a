package a.entity.gus06.system.prop.access;

import a.framework.*;

public class EntityImpl implements Entity, R, V, G, F, T {

	public String creationDate() {return "20151111";}
	
	
	public Object g() throws Exception
	{return System.getProperties();}
	
	
	public Object r(String key) throws Exception
	{return System.getProperty(key);}
	
	
	public Object t(Object obj) throws Exception
	{return System.getProperty((String) obj);}
	
	
	public void v(String key, Object obj) throws Exception
	{System.setProperty(key,(String) obj);}
	
	
	public boolean f(Object obj) throws Exception
	{return System.getProperties().containsKey((String) obj);}
}