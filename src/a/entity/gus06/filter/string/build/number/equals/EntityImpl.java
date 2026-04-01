package a.entity.gus06.filter.string.build.number.equals;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150529";}


	private Service toString;
	
	public EntityImpl() throws Exception
	{
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		Double value = toDouble(s);
		return new Filter(value);
	}
	
	
	private class Filter implements F
	{
		private Double value;
		public Filter(Double value)
		{this.value = value;}

		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			if(value==null) return false;
			String str = (String) toString.t(obj);

			Double v = toDouble(str);
			if(v==null) return false;
			return v.doubleValue()==value.doubleValue();
		}
	}


	
	

	private Double toDouble(String s)
	{
		if(s==null) return null;
		s = s.replace(" ","").replace("+","");		
		try{
			double d = Double.parseDouble(s);
			return Double.valueOf(d);
		}
		catch(Exception e)
		{return null;}
	}
}
