package a.entity.gus06.jdbc.mysql.sql.user.grant.table.usage;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150624";}


	private Service formatName;
	private Service formatUser;

	public EntityImpl() throws Exception
	{
		formatName = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");
		formatUser = Outside.service(this,"gus06.jdbc.mysql.format.sql.username");
	}



	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		String database = o[0];
		String table = o[1];
		String user = o[2];
		String host = o[3];
		
		return "GRANT USAGE ON "+formatName(database)+"."+formatName(table)+" TO "+userHost(user,host);
	}
	
	
	private String formatName(String name) throws Exception
	{return (String) formatName.t(name);}
	
	private String userHost(String user, String host) throws Exception
	{return (String) formatUser.t(new String[]{user,host});}
}
