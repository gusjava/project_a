package a.entity.gus06.sys.expression1.apply.op._tocx_mysql1_with;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170519";}
	
	public static final String URL = "jdbc:mysql://localhost/";
	public static final String USER = "root";
	


	private Service buildCx;
	
	public EntityImpl() throws Exception
	{
		buildCx = Outside.service(this,"gus06.jdbc.connection.builder");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new T1((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private String pwd;
		public T1(String pwd){this.pwd = pwd;}
		
		public Object t(Object obj) throws Exception
		{
			String dbName = (String) obj;
			String[] info = buildInfo(pwd,dbName);
			return buildCx.t(info);
		}
	}
	
	
	private String[] buildInfo(String pwd, String dbName)
	{return new String[]{URL+dbName,USER,pwd};}
}
