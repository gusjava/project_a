package a.entity.gus06.app.inside.help1;

import a.framework.*;

public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20161021";}


	private Service inside;
	
	public EntityImpl() throws Exception
	{inside = Outside.service(this,"inside");}
	
	
	public Object t(Object obj) throws Exception
	{
		String id = (String) obj;
		String path = "help1/"+id;
		return inside.t("utf8."+path);
	}
	
	public Object r(String key) throws Exception
	{return t(key);}
}
