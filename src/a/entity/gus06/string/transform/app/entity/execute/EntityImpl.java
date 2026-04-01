package a.entity.gus06.string.transform.app.entity.execute;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150324";}


	private Service unique;


	public EntityImpl() throws Exception
	{
		unique = Outside.service(this,"entityunique");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String entityName = (String) obj;
		
		try
		{
			E entity = (E) unique.t(entityName);
			entity.e();
			return entityName+" [DONE]";
		}
		catch(Exception e)
		{
			Outside.err(this,"t(Object)",e);
			return e.toString();
		}
	}
}
