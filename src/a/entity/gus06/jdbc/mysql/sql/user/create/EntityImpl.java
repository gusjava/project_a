package a.entity.gus06.jdbc.mysql.sql.user.create;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150624";}


	private Service formatUser;
	private Service formatPwd;

	public EntityImpl() throws Exception
	{
		formatUser = Outside.service(this,"gus06.jdbc.mysql.format.sql.username");
		formatPwd = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");
	}



	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String user = o[0];
		String host = o[1];
		String pwd = o[2];
		
		return "CREATE USER "+userHost(user,host)+pwdPart(pwd);
	}
	
	
	private String userHost(String user, String host) throws Exception
	{
		return (String) formatUser.t(new String[]{user,host});
	}
	
	
	private String pwdPart(String pwd) throws Exception
	{
		if(pwd!=null) return " IDENTIFIED BY "+formatPwd.t(pwd);
		return "";
	}
}
