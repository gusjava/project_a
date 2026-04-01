package a.entity.gus06.icon.provider.flag.country;

import a.framework.*;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250531";}


	private Service provider;
	
	public EntityImpl() throws Exception
	{provider = Outside.service(this,"gus06.icon.provider");}
	
	public Object t(Object obj) throws Exception
	{
		String iconKey = "FLAG_"+obj;
		return provider.t(iconKey);
	}
}