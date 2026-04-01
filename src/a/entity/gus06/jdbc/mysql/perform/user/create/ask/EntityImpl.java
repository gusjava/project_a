package a.entity.gus06.jdbc.mysql.perform.user.create.ask;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150625";}
	


	private Service create;
	private Service dialog;


	public EntityImpl() throws Exception
	{
		create = Outside.service(this,"gus06.jdbc.mysql.perform.user.create");
		dialog = Outside.service(this,"gus06.input.text.dialog");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Connection cx = (Connection) obj;
		
		String user = (String) dialog.t("Enter user:");
		if(user==null || user.equals("")) return false;
		
		String host = (String) dialog.t("Enter host:");
		if(host==null || host.equals("")) return false;
		
		String pwd = (String) dialog.t("Enter password:");
		if(pwd==null || pwd.equals("")) return false;
		
		create.p(new Object[]{cx,user,host,pwd});
		return true;
	}
}
