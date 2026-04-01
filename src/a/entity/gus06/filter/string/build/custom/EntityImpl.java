package a.entity.gus06.filter.string.build.custom;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}

	private Service unique;
	
	public EntityImpl() throws Exception
	{unique = Outside.service(this,"entityunique");}

	public Object t(Object obj) throws Exception
	{return unique.t(obj);}
}
