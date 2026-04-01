package a.entity.gus06.jdbc.mysql.sql.table.alter;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150624";}


	public final static String TYPE_ADD = "ADD";
	public final static String TYPE_DROP = "DROP";
	public final static String TYPE_CHANGE = "CHANGE";
	public final static String TYPE_MODIFY = "MODIFY";
	
	
	
	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}




	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length<2) throw new Exception("Wrong data number: "+o.length);
		
		String path = o[0];
		String type = o[1].toUpperCase();
		
		if(type.equals(TYPE_ADD))
		{
			if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
			String fieldName = o[2];
			String fieldType = o[3];
			return sql_alterTable_add(path,fieldName,fieldType);
		}
		if(type.equals(TYPE_DROP))
		{
			if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
			String fieldName = o[2];
			return sql_alterTable_drop(path,fieldName);
		}
		if(type.equals(TYPE_CHANGE))
		{
			if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
			String fieldName = o[2];
			String newName = o[3];
			String fieldType = o[4];
			return sql_alterTable_change(path,fieldName,newName,fieldType);
		}
		if(type.equals(TYPE_MODIFY))
		{
			if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
			String fieldName = o[2];
			String fieldType = o[3];
			return sql_alterTable_modify(path,fieldName,fieldType);
		}
		throw new Exception("Unkown table alter type: "+type);
	}


	
	
	
	
	private String sql_alterTable_add(String path, String fieldName, String fieldType) throws Exception
	{return "ALTER TABLE "+format(path)+" ADD "+format(fieldName)+" "+fieldType;}
	
	private String sql_alterTable_drop(String path, String fieldName) throws Exception
	{return "ALTER TABLE "+format(path)+" DROP "+format(fieldName);}
	
	private String sql_alterTable_change(String path, String fieldName, String newName, String fieldType) throws Exception
	{return "ALTER TABLE "+format(path)+" CHANGE "+format(fieldName)+" "+format(newName)+" "+fieldType;}
	
	private String sql_alterTable_modify(String path, String fieldName, String fieldType) throws Exception
	{return "ALTER TABLE "+format(path)+" MODIFY "+format(fieldName)+" "+fieldType;}
	
	
	
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
