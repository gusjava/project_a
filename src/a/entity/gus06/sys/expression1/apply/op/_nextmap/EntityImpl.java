package a.entity.gus06.sys.expression1.apply.op._nextmap;

import a.framework.*;
import java.sql.ResultSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151115";}


	private Service next;
	
	public EntityImpl() throws Exception
	{
		next = Outside.service(this,"gus06.jdbc.resultset.next.toobjectmap");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof ResultSet) return next.t((ResultSet) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
