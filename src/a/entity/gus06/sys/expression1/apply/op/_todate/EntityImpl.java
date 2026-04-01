package a.entity.gus06.sys.expression1.apply.op._todate;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160615";}


	private Service findDate;
	
	public EntityImpl() throws Exception
	{
		findDate = Outside.service(this,"gus06.find.date");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Date) return obj;
		if(obj instanceof Long) return findDate.t(obj);
		if(obj instanceof String) return findDate.t(obj);
		if(obj instanceof int[]) return findDate.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
