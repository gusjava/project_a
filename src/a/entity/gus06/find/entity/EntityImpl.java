package a.entity.gus06.find.entity;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140907";}


	private Service newEntity;
	private Service uniqueEntity;


	public EntityImpl() throws Exception
	{
		newEntity = Outside.service(this,"entitynew");
		uniqueEntity = Outside.service(this,"entityunique");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Entity) return obj;
		if(obj instanceof Class) return classToEntity((Class) obj);
		if(obj instanceof String) return stringToEntity((String) obj);
		return null;
	}
	
	
	
	private Entity classToEntity(Class c) throws Exception
	{
		if(!c.isAssignableFrom(Entity.class))
			throw new Exception("Invalid entity class: "+c);
		return (Entity) c.newInstance();
	}
	
	
	
	private Entity stringToEntity(String rule) throws Exception
	{
		if(rule.startsWith("*"))
			return (Entity) newEntity.t(rule.substring(1));
		return (Entity) uniqueEntity.t(rule);
	}
}
