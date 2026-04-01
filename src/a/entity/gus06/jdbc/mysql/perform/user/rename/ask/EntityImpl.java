package a.entity.gus06.jdbc.mysql.perform.user.rename.ask;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150625";}


	private Service rename;
	private Service dialog;


	public EntityImpl() throws Exception
	{
		rename = Outside.service(this,"gus06.jdbc.mysql.perform.db.rename");
		dialog = Outside.service(this,"gus06.input.text.dialog");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String oldName = (String) o[1];
		
		String[] n = oldName.split("@");
		String user1 = n[0];
		String host1 = n[1];
		
		
		String user2 = (String) dialog.t(new String[]{"Enter new user:",user1});
		if(user2==null || user2.equals("")) return false;
		
		String host2 = (String) dialog.t(new String[]{"Enter new host:",host1});
		if(host2==null || host2.equals("")) return false;
		
		
		rename.p(new Object[]{cx,user1,host1,user2,host2});
		return true;
	}
}
