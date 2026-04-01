package a.entity.gus06.jdbc.mysql.sql.select.count.where;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161008";}


	
	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}
	
	
		
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object path = o[0];
		String where = (String) o[1];
		
		if(where==null) return "SELECT COUNT(*) FROM "+format(path);
		if(where.equals("")) throw new Exception("Invalid empty where clause");
		
		return "SELECT COUNT(*) FROM "+format(path)+" WHERE "+where;
	}
	
	private String format(Object path) throws Exception
	{return (String) format.t(path);}
}