package a.entity.gus06.jdbc.mysql.sql.select.all.limit;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161008";}


	
	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}
	
	
		
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String path = o[0];
		String limit = o[1];
		
		int l = Integer.parseInt(limit);
		if(l<0) throw new Exception("Invalid limit value: "+l);
		
		return "SELECT * FROM "+format(path)+" LIMIT "+l;
	}
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
