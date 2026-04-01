package a.entity.gus06.jdbc.mysql.sql.select.c1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160503";}


	
	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}
	
	
		
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String path = (String) o[0];
		String c1 = (String) o[1];
		
		return "SELECT "+format(c1)+" FROM "+format(path);
	}
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
