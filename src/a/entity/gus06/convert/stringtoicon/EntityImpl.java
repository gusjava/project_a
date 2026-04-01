package a.entity.gus06.convert.stringtoicon;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140729";}


	private Service iconProvider;

	public EntityImpl() throws Exception
	{iconProvider = Outside.service(this,"gus06.icon.provider");}
	
	public Object t(Object obj) throws Exception
	{return iconProvider.t(obj);}
}
