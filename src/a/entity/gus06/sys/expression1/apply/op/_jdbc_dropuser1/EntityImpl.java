package a.entity.gus06.sys.expression1.apply.op._jdbc_dropuser1;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161101";}
	
	public static final String HOST = "%";


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.user.drop");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Connection) return new T1((Connection) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Connection cx;
		public T1(Connection cx) {this.cx = cx;}
		
		public Object t(Object obj) throws Exception
		{
			String user = (String) obj;
			return new E1(cx,user);
		}
	}
	
	
	private class E1 implements E
	{
		private Connection cx;
		private String user;
		
		public E1(Connection cx, String user)
		{
			this.cx = cx;
			this.user = user;
		}
		
		public void e() throws Exception
		{perform.p(new Object[]{cx,user,HOST});}
	}
}
