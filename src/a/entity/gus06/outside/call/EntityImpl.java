package a.entity.gus06.outside.call;

import a.framework.*;

public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20141007";}


	public Object t(Object obj) throws Exception
	{return r((String) obj);}
	
	
	public Object r(String key) throws Exception
	{return Outside.resource(this,key);}
}
