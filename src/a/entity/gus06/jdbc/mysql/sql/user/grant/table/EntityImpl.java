package a.entity.gus06.jdbc.mysql.sql.user.grant.table;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150624";}


	private Service formatPriv;
	private Service formatName;
	private Service formatUser;

	public EntityImpl() throws Exception
	{
		formatPriv = Outside.service(this,"gus06.jdbc.mysql.format.sql.sequence");
		formatName = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");
		formatUser = Outside.service(this,"gus06.jdbc.mysql.format.sql.username");
	}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
		
		Object priv = o[0];
		String database = (String) o[1];
		String table = (String) o[2];
		String user = (String) o[3];
		String host = (String) o[4];
		
		return "GRANT "+formatPriv(priv)+" ON "+formatName(database)+"."+formatName(table)+" TO "+userHost(user,host);
	}
	
	
	private String formatPriv(Object priv) throws Exception
	{return (String) formatPriv.t(priv);}
	
	private String formatName(String name) throws Exception
	{return (String) formatName.t(name);}
	
	private String userHost(String user, String host) throws Exception
	{return (String) formatUser.t(new String[]{user,host});}
}
