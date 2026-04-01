package a.entity.gus06.command.prop2.set;

import a.framework.*;

public class EntityImpl implements Entity, P, V, R {

	public String creationDate() {return "20140808";}

	private Service set0;
	private Service set1;
	
	public EntityImpl() throws Exception
	{
		set0 = Outside.service(this,"gus06.command.appfile.set");
		set1 = Outside.service(this,"gus06.command.prop.set");
	}
	
	
	public void p(Object obj) throws Exception
	{
		set0.p(obj);
		set1.p(obj);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		set0.v(key,obj);
		set1.v(key,obj);
	}
	
	public Object r(String key) throws Exception
	{return set1.r(key);}
		
}
