package a.entity.gus06.data.buildholder.double1;

import a.framework.*;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20191114";}


	
	public Object t(Object obj) throws Exception
	{return new Holder(toDouble(obj));}

	
	
	public Object g() throws Exception
	{return new Holder(Double.valueOf(0));}
	
	
	
	private class Holder extends S1 implements T, P, G
	{
		private Double data;
		
		public Holder(Double data)
		{this.data = data;}
		
		public Object g() throws Exception
		{return data;}
		
		public void p(Object obj) throws Exception
		{t(obj);}
		
		public Object t(Object obj) throws Exception
		{
			int change = getChange(obj);
			if(change!=0) modified();
			return Integer.valueOf(change);
		}
		
		
		private int getChange(Object data1) throws Exception
		{
			if(data1==null) return getChangeFromNumber(Double.valueOf(0));
			if(data1 instanceof Number) return getChangeFromNumber((Number) data1);
			if(data1 instanceof String) return getChangeFromString((String) data1);
			
			throw new Exception("Invalid data type: "+data1.getClass().getName());
		}
		
		private int getChangeFromNumber(Number data1) throws Exception
		{
			double b = data.doubleValue();
			double b1 = data1.doubleValue();
			
			data = toDouble(data1);
			return b1==b ? 0 : b1>b ? 1 : -1;
		}
		
		private int getChangeFromString(String s) throws Exception
		{
			if(s.startsWith("v+"))
			{
				double v = Double.parseDouble(s.substring(2));
				return getChangeFromNumber(data+v);
			}
			if(s.startsWith("v-"))
			{
				double v = Double.parseDouble(s.substring(2));
				return getChangeFromNumber(data-v);
			}
			if(s.startsWith("v*"))
			{
				double v = Double.parseDouble(s.substring(2));
				return getChangeFromNumber(data*v);
			}
			if(s.startsWith("v/"))
			{
				double v = Double.parseDouble(s.substring(2));
				return getChangeFromNumber(data/v);
			}
			
			if(s.startsWith("+")) s = s.substring(1);
			return getChangeFromNumber(Double.valueOf(s));
		}
		
		private void modified()
		{send(this,"modified()");}
	}
	
	
	
	private Double toDouble(Object obj) throws Exception
	{
		if(obj==null) return Double.valueOf(0);
		if(obj instanceof Double) return (Double) obj;
		if(obj instanceof Number) return Double.valueOf(((Number) obj).doubleValue());
		if(obj instanceof String) return Double.valueOf((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
