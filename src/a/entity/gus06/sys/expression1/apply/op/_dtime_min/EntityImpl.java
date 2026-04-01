package a.entity.gus06.sys.expression1.apply.op._dtime_min;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200513";}

	
	private Service findDate;
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		findDate = Outside.service(this,"gus06.find.date");
		perform = Outside.service(this,"gus06.time.date.add.minutes");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Date)		return new T1(obj);
		if(obj instanceof Long)		return new T1(findDate.t(obj));
		if(obj instanceof String)	return new T1(findDate.t(obj));
		if(obj instanceof int[])	return new T1(findDate.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private Object value;
		public T1(Object value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return perform.t(new Object[]{value,obj});}
	}
}
