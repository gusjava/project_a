package a.entity.gus06.find.boolean1.df;

import a.framework.*;

public class EntityImpl implements Entity, T, F {

	public String creationDate() {return "20250825";}


	private Service find;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.find.boolean1");
	}
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;
		return find.f(obj);
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return Boolean.FALSE;
		return find.f(obj);
	}
}