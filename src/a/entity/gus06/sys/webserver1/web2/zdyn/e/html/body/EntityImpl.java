package a.entity.gus06.sys.webserver1.web2.zdyn.e.html.body;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140930";}


	private Service findPage;
	private Service template;
	private Service uniqueEntity;


	public EntityImpl() throws Exception
	{
		findPage = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.find.page");
		template = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template");
		uniqueEntity = Outside.service(this,"entityunique");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map prop = (Map) ((R)obj).r("data config prop");
		String id = (String) findPage.t(obj);
		
		if(prop.containsKey("body."+id))
		{
			String entityName = (String) prop.get("body."+id);
			((P) uniqueEntity.t(entityName)).p(obj);
		}
		else template.p(obj);
	}
}
