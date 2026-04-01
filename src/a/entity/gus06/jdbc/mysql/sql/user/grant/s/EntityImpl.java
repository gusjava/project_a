package a.entity.gus06.jdbc.mysql.sql.user.grant.s;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150624";}


	private Service formatPriv;
	private Service formatUser;

	public EntityImpl() throws Exception
	{
		formatPriv = Outside.service(this,"gus06.jdbc.mysql.format.sql.sequence");
		formatUser = Outside.service(this,"gus06.jdbc.mysql.format.sql.username");
	}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object priv = o[0];
		String user = (String) o[1];
		String host = (String) o[2];
		
		return "GRANT "+formatPriv(priv)+" ON *.* TO "+userHost(user,host);
	}
	
	private String formatPriv(Object priv) throws Exception
	{return (String) formatPriv.t(priv);}
	
	private String userHost(String user, String host) throws Exception
	{return (String) formatUser.t(new String[]{user,host});}
}
