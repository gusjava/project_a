package a.entity.gus06.filter.string.build.number.range;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150529";}


	public static final String NUM = "(-?[0-9\\.]+)";
	
	public static final Pattern p_equals = Pattern.compile("="+NUM);
	public static final Pattern p_inf1 = Pattern.compile("<"+NUM);
	public static final Pattern p_inf0 = Pattern.compile("<="+NUM);
	public static final Pattern p_sup1 = Pattern.compile(">"+NUM);
	public static final Pattern p_sup0 = Pattern.compile(">="+NUM);
	public static final Pattern p_range11 = Pattern.compile("\\]"+NUM+" "+NUM+"\\[");
	public static final Pattern p_range00 = Pattern.compile("\\["+NUM+" "+NUM+"\\]");
	public static final Pattern p_range10 = Pattern.compile("\\]"+NUM+" "+NUM+"\\]");
	public static final Pattern p_range01 = Pattern.compile("\\["+NUM+" "+NUM+"\\[");
	
	private Matcher m;
	
	
	
	private Service toString;
	
	public EntityImpl() throws Exception
	{
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String)obj;
		
		m = p_equals.matcher(s);
		if(m.matches())
		{
			double v = Double.parseDouble(m.group(1));
			return new F_equals(v);
		}
		m = p_inf1.matcher(s);
		if(m.matches())
		{
			double v = Double.parseDouble(m.group(1));
			return new F_inf1(v);
		}
		m = p_inf0.matcher(s);
		if(m.matches())
		{
			double v = Double.parseDouble(m.group(1));
			return new F_inf0(v);
		}
		m = p_sup1.matcher(s);
		if(m.matches())
		{
			double v = Double.parseDouble(m.group(1));
			return new F_sup1(v);
		}
		m = p_sup0.matcher(s);
		if(m.matches())
		{
			double v = Double.parseDouble(m.group(1));
			return new F_sup0(v);
		}
		m = p_range11.matcher(s);
		if(m.matches())
		{
			double v1 = Double.parseDouble(m.group(1));
			double v2 = Double.parseDouble(m.group(2));
			return new F_range11(v1,v2);
		}
		m = p_range00.matcher(s);
		if(m.matches())
		{
			double v1 = Double.parseDouble(m.group(1));
			double v2 = Double.parseDouble(m.group(2));
			return new F_range00(v1,v2);
		}
		m = p_range10.matcher(s);
		if(m.matches())
		{
			double v1 = Double.parseDouble(m.group(1));
			double v2 = Double.parseDouble(m.group(2));
			return new F_range10(v1,v2);
		}
		m = p_range01.matcher(s);
		if(m.matches())
		{
			double v1 = Double.parseDouble(m.group(1));
			double v2 = Double.parseDouble(m.group(2));
			return new F_range01(v1,v2);
		}
		
		double value = Double.parseDouble(s);
		return new F_equals(value);
	}
	
	
	
	
	
	
	
	
	
	private boolean eq(String str, double value)
	{
		try{return Double.parseDouble(str)==value;}
		catch(Exception e){return false;}
	}
	
	private boolean inf0(String str, double value)
	{
		try{return Double.parseDouble(str)<=value;}
		catch(Exception e){return false;}
	}
	
	private boolean inf1(String str, double value)
	{
		try{return Double.parseDouble(str)<value;}
		catch(Exception e){return false;}
	}
	
	private boolean sup0(String str, double value)
	{
		try{return Double.parseDouble(str)>=value;}
		catch(Exception e){return false;}
	}
	
	private boolean sup1(String str, double value)
	{
		try{return Double.parseDouble(str)>value;}
		catch(Exception e){return false;}
	}

	private boolean range00(String str, double value1, double value2)
	{
		try{double v = Double.parseDouble(str);return v>=value1 && v<=value2;}
		catch(Exception e){return false;}
	}

	private boolean range11(String str, double value1, double value2)
	{
		try{double v = Double.parseDouble(str);return v>value1 && v<value2;}
		catch(Exception e){return false;}
	}

	private boolean range10(String str, double value1, double value2)
	{
		try{double v = Double.parseDouble(str);return v>value1 && v<=value2;}
		catch(Exception e){return false;}
	}

	private boolean range01(String str, double value1, double value2)
	{
		try{double v = Double.parseDouble(str);return v>=value1 && v<value2;}
		catch(Exception e){return false;}
	}

	

	
	
	
	
	
	
	
	
	
	
	
	private class F_equals implements F
	{
		private double value;
		public F_equals(double value)
		{this.value = value;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) toString.t(obj);
			return eq(str,value);
		}
	}
	
	
	
	private class F_inf0 implements F
	{
		private double value;
		public F_inf0(double value)
		{this.value = value;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) toString.t(obj);
			if(str.startsWith("<=")) return inf0(str.substring(2),value);
			if(str.startsWith("<")) return inf0(str.substring(1),value);
			return inf0(str,value);
		}
	}
	
	
	
	private class F_inf1 implements F
	{
		private double value;
		public F_inf1(double value)
		{this.value = value;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) toString.t(obj);
			if(str.startsWith("<=")) return inf1(str.substring(2),value);
			if(str.startsWith("<")) return inf0(str.substring(1),value);
			return inf1(str,value);
		}
	}
	
	
	
	private class F_sup0 implements F
	{
		private double value;
		public F_sup0(double value)
		{this.value = value;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) toString.t(obj);
			if(str.startsWith(">=")) return sup0(str.substring(2),value);
			if(str.startsWith(">")) return sup0(str.substring(1),value);
			return sup0(str,value);
		}
	}
	
	
	
	private class F_sup1 implements F
	{
		private double value;
		public F_sup1(double value)
		{this.value = value;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) toString.t(obj);
			if(str.startsWith(">=")) return sup1(str.substring(2),value);
			if(str.startsWith(">")) return sup0(str.substring(1),value);
			return sup1(str,value);
		}
	}
	
	
	
	private class F_range00 implements F
	{
		private double value1;
		private double value2;
		public F_range00(double value1, double value2)
		{this.value1 = value1;this.value2 = value2;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) toString.t(obj);
			return range00(str,value1,value2);
		}
	}
	
	
	
	private class F_range11 implements F
	{
		private double value1;
		private double value2;
		public F_range11(double value1, double value2)
		{this.value1 = value1;this.value2 = value2;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) toString.t(obj);
			return range11(str,value1,value2);
		}
	}
	
	
	
	private class F_range10 implements F
	{
		private double value1;
		private double value2;
		public F_range10(double value1, double value2)
		{this.value1 = value1;this.value2 = value2;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) toString.t(obj);
			return range10(str,value1,value2);
		}
	}
	
	
	
	private class F_range01 implements F
	{
		private double value1;
		private double value2;
		public F_range01(double value1, double value2)
		{this.value1 = value1;this.value2 = value2;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) toString.t(obj);
			return range01(str,value1,value2);
		}
	}

}
