package a.entity.gus06.sys.expression1.apply.op._millisecond;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160625";}


	private Service millisecond;
	private Service findDate;
	
	public EntityImpl() throws Exception
	{
		millisecond = Outside.service(this,"gus06.time.date.get.millisecond");
		findDate = Outside.service(this,"gus06.find.date");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Date) return millisecond(obj);
		if(obj instanceof Long) return millisecond(findDate.t(obj));
		if(obj instanceof int[]) return millisecond(findDate.t(obj));
		if(obj instanceof String) return millisecond(findDate.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object millisecond(Object obj) throws Exception
	{return millisecond.t(obj);}
}
