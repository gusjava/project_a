package a.entity.gus06.sys.expression1.apply.op._parsedate;

import a.framework.*;
import java.util.Date;
import java.text.DateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180407";}
	
	
	private Service toDateFormat;
	
	public EntityImpl() throws Exception
	{
		toDateFormat = Outside.service(this,"gus06.find.dateformat");
	}	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new T1((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class T1 implements T
	{
		private String timeStamp;
		
		public T1(String timeStamp)
		{this.timeStamp = timeStamp;}
		
		public Object t(Object obj) throws Exception
		{
			DateFormat df = (DateFormat) toDateFormat.t(obj);
			return df.parse(timeStamp);
		}
	}
}
