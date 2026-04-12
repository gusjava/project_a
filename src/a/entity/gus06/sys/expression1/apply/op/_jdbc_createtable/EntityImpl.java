package a.entity.gus06.sys.expression1.apply.op._jdbc_createtable;

import a.framework.*;
import java.sql.Connection;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160407";}

	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.generic.perform.table.create1");
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
		
		public T1(Connection cx)
		{this.cx = cx;}
		
		public Object t(Object obj) throws Exception
		{
			String path = (String) obj;
			return new T2(cx,path);
		}
	}
	
	private class T2 implements T
	{
		private Connection cx;
		private String path;
		
		public T2(Connection cx, String path)
		{
			this.cx = cx;
			this.path = path;
		}
		
		public Object t(Object obj) throws Exception
		{
			Map map = (Map) obj;
			return new E1(cx,path,map);
		}
	}
	
	private class E1 implements E
	{
		private Connection cx;
		private String path;
		private Map map;
		
		public E1(Connection cx, String path, Map map)
		{
			this.cx = cx;
			this.path = path;
			this.map = map;
		}
		
		public void e() throws Exception
		{perform.p(new Object[]{cx,path,map});}
	}
}
