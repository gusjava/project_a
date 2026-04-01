package a.entity.gus06.sys.expression1.apply.op._between01;

import a.framework.*;
import java.util.Date;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231101";}


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
		if(obj instanceof Number) return new FNumber(toDouble(obj));
		if(obj instanceof Date) return new FDate(toTime(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private double toDouble(Object obj)
	{
		return Double.parseDouble(""+obj);
	}
	
	private long toTime(Object obj) throws Exception
	{
		if(obj instanceof Long) return ((Long) obj).longValue();
		if(obj instanceof Date) return ((Date) obj).getTime();
		if(obj instanceof String) return ((Date) findDate.t(obj)).getTime();
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class FNumber implements F
	{
		private double value;
		public FNumber(double value) {this.value = value;}
		
		public boolean f(Object obj) throws Exception
		{
			double[] range = findDoubleRange(obj);
			return range[0] < value && value <= range[1];
		}
	}
	
	private class FDate implements F
	{
		private long value;
		public FDate(long value) {this.value = value;}
		
		public boolean f(Object obj) throws Exception
		{
			long[] range = findDateRange(obj);
			return range[0] < value && value <= range[1];
		}
	}
	
	
	
	private double[] findDoubleRange(Object obj) throws Exception
	{
		if(obj instanceof double[])
		{
			double[] dd = (double[]) obj;
			if(dd.length!=2) throw new Exception("Invalid array length: "+dd.length);
			return dd;
		}
		if(obj instanceof int[])
		{
			int[] dd = (int[]) obj;
			if(dd.length!=2) throw new Exception("Invalid array length: "+dd.length);
			return new double[]{(double) dd[0], (double) dd[1]};
		}
		if(obj instanceof long[])
		{
			long[] dd = (long[]) obj;
			if(dd.length!=2) throw new Exception("Invalid array length: "+dd.length);
			return new double[]{(double) dd[0], (double) dd[1]};
		}
		if(obj instanceof Object[])
		{
			Object[] dd = (Object[]) obj;
			if(dd.length!=2) throw new Exception("Invalid array length: "+dd.length);
			return new double[]{toDouble(dd[0]), toDouble(dd[1])};
		}
		if(obj instanceof List)
		{
			List dd = (List) obj;
			if(dd.size()!=2) throw new Exception("Invalid list size: "+dd.size());
			return new double[]{toDouble(dd.get(0)), toDouble(dd.get(1))};
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private long[] findDateRange(Object obj) throws Exception
	{
		if(obj instanceof long[])
		{
			long[] dd = (long[]) obj;
			if(dd.length!=2) throw new Exception("Invalid array length: "+dd.length);
			return dd;
		}
		if(obj instanceof Object[])
		{
			Object[] dd = (Object[]) obj;
			if(dd.length!=2) throw new Exception("Invalid array length: "+dd.length);
			return new long[]{toTime(dd[0]), toTime(dd[1])};
		}
		if(obj instanceof List)
		{
			List dd = (List) obj;
			if(dd.size()!=2) throw new Exception("Invalid list size: "+dd.size());
			return new long[]{toTime(dd.get(0)), toTime(dd.get(1))};
		}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}