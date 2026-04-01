package a.entity.gus06.sys.expression1.apply.op._second;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160625";}


	private Service second;
	private Service findDate;
	
	public EntityImpl() throws Exception
	{
		second = Outside.service(this,"gus06.time.date.get.second");
		findDate = Outside.service(this,"gus06.find.date");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Date) return second(obj);
		if(obj instanceof Long) return second(findDate.t(obj));
		if(obj instanceof int[]) return second(findDate.t(obj));
		if(obj instanceof String) return second(findDate.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object second(Object obj) throws Exception
	{return second.t(obj);}
}
