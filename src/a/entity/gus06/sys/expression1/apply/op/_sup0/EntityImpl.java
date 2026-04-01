package a.entity.gus06.sys.expression1.apply.op._sup0;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Number) return new FNumber(toDouble(obj));
		if(obj instanceof Date) return new FDate(toTime(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private double toDouble(Object obj)
	{return Double.parseDouble(""+obj);}
	
	private long toTime(Object obj)
	{return ((Date) obj).getTime();}
	
	
	private class FNumber implements F
	{
		private double value;
		public FNumber(double value) {this.value = value;}
		
		public boolean f(Object obj) throws Exception
		{return value > toDouble(obj);}
	}
	
	private class FDate implements F
	{
		private long value;
		public FDate(long value) {this.value = value;}
		
		public boolean f(Object obj) throws Exception
		{return value > toTime(obj);}
	}
}