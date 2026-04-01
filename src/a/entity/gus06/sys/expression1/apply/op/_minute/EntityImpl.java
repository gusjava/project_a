package a.entity.gus06.sys.expression1.apply.op._minute;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160625";}


	private Service minute;
	private Service findDate;
	
	public EntityImpl() throws Exception
	{
		minute = Outside.service(this,"gus06.time.date.get.minute");
		findDate = Outside.service(this,"gus06.find.date");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Date) return minute(obj);
		if(obj instanceof Long) return minute(findDate.t(obj));
		if(obj instanceof int[]) return minute(findDate.t(obj));
		if(obj instanceof String) return minute(findDate.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object minute(Object obj) throws Exception
	{return minute.t(obj);}
}
