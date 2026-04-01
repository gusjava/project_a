package a.entity.gus06.app.entity.executeunique;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150831";}

	private Service uniqueEntity;
	
	
	public EntityImpl() throws Exception
	{
		uniqueEntity = Outside.service(this,"entityunique");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		String entityName = (String) obj;
		Object entity = uniqueEntity.t(entityName);
		
		if(!(entity instanceof E))
			throw new Exception("Entity "+entityName+" is not executable");
		((E) entity).e();
	}
}
