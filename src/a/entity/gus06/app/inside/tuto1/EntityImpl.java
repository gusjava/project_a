package a.entity.gus06.app.inside.tuto1;

import a.framework.*;

public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20160701";}


	private Service inside;
	
	public EntityImpl() throws Exception
	{inside = Outside.service(this,"inside");}
	
	
	public Object t(Object obj) throws Exception
	{
		String id = (String) obj;
		String path = "tuto1/"+id;
		return inside.t("prop."+path);
	}
	
	public Object r(String key) throws Exception
	{return t(key);}
}
