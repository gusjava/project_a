package a.entity.gus06.maincust.icon.rb;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140812";}


	private Service iconProvider;

	public EntityImpl() throws Exception
	{
		iconProvider = Outside.service(this,"gus06.icon.provider");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String buildInfo = (String) o[0];
		Object src = o[1];
		
		return iconProvider.r(buildInfo);
	}
}
