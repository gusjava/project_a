package a.entity.gus06.sys.expression1.apply.op._tocx_mysql1;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170513";}
	
	public static final String URL = "jdbc:mysql://localhost/mysql";
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
		if(obj instanceof String) return buildCx.t(buildInfo((String) obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String[] buildInfo(String pwd)
	{return new String[]{URL,USER,pwd};}
}
