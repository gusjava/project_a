package a.entity.gus06.app.inside.ling;

import a.framework.*;

public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20140808";}


	private Service inside;
	
	public EntityImpl() throws Exception
	{inside = Outside.service(this,"inside");}
	
	
	public Object t(Object obj) throws Exception
	{
		String id = (String) obj;
		String path = "ling/"+id;
		return inside.t("prop."+path);
	}
	
	public Object r(String key) throws Exception
	{return t(key);}
}
