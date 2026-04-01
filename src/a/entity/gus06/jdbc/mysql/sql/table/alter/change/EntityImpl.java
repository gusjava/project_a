package a.entity.gus06.jdbc.mysql.sql.table.alter.change;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190323";}
	
	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}


	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		String path = o[0];
		String fieldName = o[1];
		String newName = o[2];
		String fieldType = o[3];
		
		return "ALTER TABLE "+format(path)+" CHANGE "+format(fieldName)+" "+format(newName)+" "+fieldType;
	}
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
