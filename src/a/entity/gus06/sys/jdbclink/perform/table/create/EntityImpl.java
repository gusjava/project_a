package a.entity.gus06.sys.jdbclink.perform.table.create;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20150625";}
	
	
	public final static String F_EL1 = "EL1";
	public final static String F_EL2 = "EL2";
	
	public final static String T_EL1 = "CHAR(100)";
	public final static String T_EL2 = "CHAR(100)";

	

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
		
		String[] col = new String[]{F_EL1,F_EL2};
		String[] type = new String[]{T_EL1,T_EL2};
		String[] primary = new String[]{F_EL1,F_EL2};
		
		create.p(new Object[]{cx,path,col,type,primary,null,null});
		return true;
	}
}
