package a.entity.gus06.jdbc.mysql.sql.user.revoke.db.usage;

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
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String database = o[0];
		String user = o[1];
		String host = o[2];
		
		return "REVOKE USAGE ON "+formatName(database)+".* FROM "+userHost(user,host);
	}
	
	
	private String formatName(Object priv) throws Exception
	{return (String) formatName.t(priv);}
	
	private String userHost(String user, String host) throws Exception
	{return (String) formatUser.t(new String[]{user,host});}
}
