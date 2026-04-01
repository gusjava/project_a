package a.entity.gus06.sys.expression1.apply.op._duration_hour;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190529";}


	private Service perform;
	private Service findDate;
		
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.time.duration.between.hour");
		findDate = Outside.service(this,"gus06.find.date");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Date) return new T1((Date) obj);
		if(obj instanceof Long) return new T1((Date) findDate.t(obj));
		if(obj instanceof String) return new T1((Date) findDate.t(obj));
		if(obj instanceof int[]) return new T1((Date) findDate.t(obj));
		
		if(obj instanceof Date[]) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Date value;
		public T1(Date value) {this.value = value;}
		
		public Object t(Object obj) throws Exception
		{return perform.t(new Date[]{value,(Date) findDate.t(obj)});}
	}
}
