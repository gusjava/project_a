package a.entity.gus06.sys.expression1.apply.op._dayofweek_fr;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160614";}


	private Service dayOfWeek;
	private Service findDate;
	
	public EntityImpl() throws Exception
	{
		dayOfWeek = Outside.service(this,"gus06.time.date.dayofweek.fr");
		findDate = Outside.service(this,"gus06.find.date");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Date) return dayOfWeek(obj);
		if(obj instanceof Long) return dayOfWeek(findDate.t(obj));
		if(obj instanceof int[]) return dayOfWeek(findDate.t(obj));
		if(obj instanceof String) return dayOfWeek(findDate.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Object dayOfWeek(Object obj) throws Exception
	{return dayOfWeek.t(obj);}
}
