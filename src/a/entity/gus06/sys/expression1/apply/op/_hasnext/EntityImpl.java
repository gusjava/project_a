package a.entity.gus06.sys.expression1.apply.op._hasnext;

import a.framework.*;
import java.sql.ResultSet;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151115";}


	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof ResultSet) return Boolean.valueOf(((ResultSet)obj).next());
		if(obj instanceof Iterator) return Boolean.valueOf(((Iterator)obj).hasNext());
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
