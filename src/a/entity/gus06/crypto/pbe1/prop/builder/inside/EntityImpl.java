package a.entity.gus06.crypto.pbe1.prop.builder.inside;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150625";}
	
	public static final String PATH = "pbe1/prop";


	private Service decrypt;
	private Service inside;
	
	
	public EntityImpl() throws Exception
	{
		decrypt = Outside.service(this,"gus06.crypto.pbe1.object.decrypt");
		inside = Outside.service(this,"inside");
	}
	
	public Object g() throws Exception
	{
		Map p = (Map) inside.t("prop."+PATH);
		if(p==null) return null;
		return decrypt.t(p);
	}
}
