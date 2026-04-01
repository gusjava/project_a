package a.entity.gus06.jdbc.mysql.sql.select.all.where;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161007";}


	
	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}
	
	
		
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String path = o[0];
		String where = o[1];
		
		if(where==null) return "SELECT * FROM "+format(path);
		if(where.equals("")) throw new Exception("Invalid empty where clause");
		
		return "SELECT * FROM "+format(path)+" WHERE "+where;
	}
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}