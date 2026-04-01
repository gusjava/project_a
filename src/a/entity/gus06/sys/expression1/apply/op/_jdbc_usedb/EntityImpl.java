package a.entity.gus06.sys.expression1.apply.op._jdbc_usedb;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231016";}


	private Service sqlQuery;
	
	public EntityImpl() throws Exception
	{
		sqlQuery = Outside.service(this,"gus06.jdbc.mysql.perform.cx.usedb");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Connection) return new P1((Connection) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class P1 implements P
	{
		private Connection cx;
		public P1(Connection cx) {this.cx = cx;}
		
		public void p(Object obj) throws Exception
		{sqlQuery.p(new Object[]{cx,obj});}
	}
}