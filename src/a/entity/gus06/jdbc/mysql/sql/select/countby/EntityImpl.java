package a.entity.gus06.jdbc.mysql.sql.select.countby;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231107";}


	
	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}
	
	
		
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String path = format(o[0]);
		String colName = format(o[1]);
		
		return "SELECT "+colName+", COUNT("+colName+") as count FROM "+path+" GROUP BY "+colName;
	}
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}