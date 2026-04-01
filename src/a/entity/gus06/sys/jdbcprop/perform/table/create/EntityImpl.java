package a.entity.gus06.sys.jdbcprop.perform.table.create;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20150625";}
	
	
	public final static String F_ID = "ID";
	public final static String F_VALUE = "VALUE";
	
	public final static String T_ID = "CHAR(100)";
	public final static String T_VALUE = "TEXT";

	

	private Service create;

	public EntityImpl() throws Exception
	{
		create = Outside.service(this,"gus06.jdbc.mysql.perform.table.create");
	}
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		
		String[] col = new String[]{F_ID,F_VALUE};
		String[] type = new String[]{T_ID,T_VALUE};
		String[] primary = new String[]{F_ID};
		
		create.p(new Object[]{cx,path,col,type,primary,null,null});
		return true;
	}
}
