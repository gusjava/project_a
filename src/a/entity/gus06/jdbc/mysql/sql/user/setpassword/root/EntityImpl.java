package a.entity.gus06.jdbc.mysql.sql.user.setpassword.root;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150624";}


	private Service formatPwd;

	public EntityImpl() throws Exception
	{
		formatPwd = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");
	}
	
	
	public Object t(Object obj) throws Exception
	{		
		String pwd = (String) obj;
		return "SET PASSWORD FOR 'root'@'%' = PASSWORD("+formatPwd(pwd)+")";
	}
	
	
	private String formatPwd(String pwd) throws Exception
	{return (String) formatPwd.t(pwd);}
}