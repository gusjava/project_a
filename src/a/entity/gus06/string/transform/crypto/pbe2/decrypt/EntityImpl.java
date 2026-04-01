package a.entity.gus06.string.transform.crypto.pbe2.decrypt;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151115";}


	private Service s;

	public EntityImpl() throws Exception
	{s = Outside.service(this,"gus06.crypto.pbe2.string.decrypt");}
	
	public Object t(Object obj) throws Exception
	{return s.t(obj);}
}
