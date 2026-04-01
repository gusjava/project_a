package a.entity.gus06.sys.jdbcblob.perform.table.create;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20150625";}
	
	
	public final static String F_ID = "id";
	public final static String F_NAME = "name";
	public final static String F_TYPE = "type";
	public final static String F_SIZE = "size";
	public final static String F_TIME = "time";
	public final static String F_PREVIEW = "preview";
	public final static String F_DATA = "data";
	
	public final static String T_ID = "CHAR(32)";
	public final static String T_NAME = "CHAR(255)";
	public final static String T_TYPE = "CHAR(10)";
	public final static String T_SIZE = "INT";
	public final static String T_TIME = "DATETIME";
	public final static String T_PREVIEW = "MEDIUMBLOB";
	public final static String T_DATA = "LONGBLOB";

	

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
		
		String[] col = new String[]{F_ID,F_NAME,F_TYPE,F_SIZE,F_TIME,F_PREVIEW,F_DATA};
		String[] type = new String[]{T_ID,T_NAME,T_TYPE,T_SIZE,T_TIME,T_PREVIEW,T_DATA};
		String[] primary = new String[]{F_ID};
		
		create.p(new Object[]{cx,path,col,type,primary,null,null});
		return true;
	}
}
