package a.entity.gus06.data.filter.isnempty;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20200112";}


	private Service isEmpty;
	
	public EntityImpl() throws Exception
	{isEmpty = Outside.service(this,"gus06.data.filter.isempty");}
	
	public boolean f(Object obj) throws Exception
	{return !isEmpty.f(obj);}
}
