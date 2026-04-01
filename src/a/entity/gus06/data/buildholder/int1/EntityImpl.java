package a.entity.gus06.data.buildholder.int1;

import a.framework.*;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20161013";}


	
	public Object t(Object obj) throws Exception
	{return new Holder(toInteger(obj));}

	
	
	public Object g() throws Exception
	{return new Holder(Integer.valueOf(0));}
	
	
	
	private class Holder extends S1 implements T, P, G
	{
		private Integer data;
		
		public Holder(Integer data)
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
			if(data1==null) return getChangeFromInteger(Integer.valueOf(0));
			if(data1 instanceof Integer) return getChangeFromInteger((Integer) data1);
			if(data1 instanceof String) return getChangeFromString((String) data1);
			
			throw new Exception("Invalid data type: "+data1.getClass().getName());
		}
		
		private int getChangeFromInteger(Integer data1)
		{
			int b = data.intValue();
			int b1 = data1.intValue();
			
			data = data1;
			return b1==b ? 0 : b1>b ? 1 : -1;
		}
		
		private int getChangeFromString(String s) throws Exception
		{
			if(s.startsWith("v+"))
			{
				int v = Integer.parseInt(s.substring(2));
				return getChangeFromInteger(data+v);
			}
			if(s.startsWith("v-"))
			{
				int v = Integer.parseInt(s.substring(2));
				return getChangeFromInteger(data-v);
			}
			if(s.startsWith("v*"))
			{
				int v = Integer.parseInt(s.substring(2));
				return getChangeFromInteger(data*v);
			}
			if(s.startsWith("v/"))
			{
				int v = Integer.parseInt(s.substring(2));
				return getChangeFromInteger(data/v);
			}
			
			if(s.startsWith("+")) s = s.substring(1);
			return getChangeFromInteger(Integer.valueOf(s));
		}
		
		private void modified()
		{send(this,"modified()");}
	}
	
	
	
	private Integer toInteger(Object obj) throws Exception
	{
		if(obj==null) return Integer.valueOf(0);
		if(obj instanceof Integer) return (Integer) obj;
		if(obj instanceof String) return Integer.valueOf((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
