package a.entity.gus06.app.entity.nametoclassentry;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140829";}

	public Object t(Object obj) throws Exception
	{
		String name = (String) obj;
		return "gus06/entity/"+name.replace(".","/")+"/EntityImpl.class";
	}
}
