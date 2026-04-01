package a.entity.gus06.feature.build.f.id;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160820";}

	public boolean f(Object obj) throws Exception
	{return ((Boolean) obj).booleanValue();}
}
