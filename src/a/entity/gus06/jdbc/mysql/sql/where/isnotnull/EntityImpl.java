package a.entity.gus06.jdbc.mysql.sql.where.isnotnull;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231112";}


	private Service formatName;
	
	public EntityImpl() throws Exception
	{formatName = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}

	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof String) return perform((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String perform(String name) throws Exception
	{return formatName(name)+" IS NOT NULL";}
	
	private String formatName(String name) throws Exception
	{return (String) formatName.t(name);}
}