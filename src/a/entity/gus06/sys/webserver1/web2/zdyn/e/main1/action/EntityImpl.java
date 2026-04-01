package a.entity.gus06.sys.webserver1.web2.zdyn.e.main1.action;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141008";}
	
	private Service findAction;
	private Service uniqueEntity;


	public EntityImpl() throws Exception
	{
		findAction = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.find.action");
		uniqueEntity = Outside.service(this,"entityunique");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map prop = (Map) ((R)obj).r("data config prop");
		String id = (String) findAction.t(obj);
		
		if(prop.containsKey("action."+id))
		{
			String entityName = (String) prop.get("action."+id);
			((P) uniqueEntity.t(entityName)).p(obj);
		}
	}
}
