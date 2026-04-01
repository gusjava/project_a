package a.entity.gus06.sys.base1.holder.jdbc;

import a.framework.*;

public class EntityImpl implements Entity, P, R, V, F, G, E {

	public String creationDate() {return "20150808";}


	private Service builder;
	private Service g_jdbc;
	
	private Object holder;


	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.base1.builder");
		g_jdbc = Outside.service(this,"gus06.sys.base1.holder.jdbc.g");
		
		holder = builder.t(g_jdbc);
	}
	
	
	
	public Object g() throws Exception
	{return ((G) holder).g();}
	
	public boolean f(Object obj) throws Exception
	{return ((F) holder).f(obj);}
	
	public Object r(String key) throws Exception
	{return ((R) holder).r(key);}
	
	public void v(String key, Object obj) throws Exception
	{((V) holder).v(key,obj);}
	
	public void p(Object obj) throws Exception
	{((P) holder).p(obj);}
	
	public void e() throws Exception
	{((E) holder).e();}
}
