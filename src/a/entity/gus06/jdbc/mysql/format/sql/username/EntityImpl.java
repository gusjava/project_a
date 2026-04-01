package a.entity.gus06.jdbc.mysql.format.sql.username;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161101";}


	private Service format;

	public EntityImpl() throws Exception
	{
		format = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String user = o[0];
		String host = o[1];
		
		if(user==null || user.equals("")) user = "%";
		if(host==null || host.equals("")) host = "%";
		
		return format(user)+"@"+format(host);
	}
	
	
	private String format(String value) throws Exception
	{return (String) format.t(value);}
}
