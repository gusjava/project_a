package a.entity.gus06.sys.expression1.apply.op._formatdate;

import a.framework.*;
import java.util.Date;
import java.text.DateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161120";}
	
	
	private Service toDateFormat;
	private Service findDate;
	
	public EntityImpl() throws Exception
	{
		toDateFormat = Outside.service(this,"gus06.find.dateformat");
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
		if(obj instanceof int[]) return new T1((Date) findDate.t(obj));
		if(obj instanceof String) return new T1((Date) findDate.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private Date date;
		
		public T1(Date date)
		{this.date = date;}
		
		public Object t(Object obj) throws Exception
		{
			DateFormat df = (DateFormat) toDateFormat.t(obj);
			return df.format(date);
		}
	}
}