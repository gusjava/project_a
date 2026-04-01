package a.entity.gus06.jdbc.mysql.sql.table.duplicate;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150624";}


	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}


	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String path1 = o[0];
		String path2 = o[1];
		
		return "CREATE TABLE "+format(path2)+" SELECT * FROM "+format(path1);
	}

	private String format(String s) throws Exception
	{return (String) format.t(s);}
}