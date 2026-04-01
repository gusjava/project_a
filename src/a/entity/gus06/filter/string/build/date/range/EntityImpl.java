package a.entity.gus06.filter.string.build.date.range;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}

	public static final String DATE = "([0-9][0-9][0-9][0-9]\\.[0-9#][0-9#]\\.[0-9#][0-9#])";
	public static final String DATE0 = "([0-9#][0-9#][0-9#][0-9#]\\.[0-9#][0-9#]\\.[0-9#][0-9#])";
	
	public static final Pattern p_equals = Pattern.compile("="+DATE0);
	public static final Pattern p_inf1 = Pattern.compile("<"+DATE);
	public static final Pattern p_inf0 = Pattern.compile("<="+DATE);
	public static final Pattern p_sup1 = Pattern.compile(">"+DATE);
	public static final Pattern p_sup0 = Pattern.compile(">="+DATE);
	public static final Pattern p_range11 = Pattern.compile("\\]"+DATE+" "+DATE+"\\[");
	public static final Pattern p_range00 = Pattern.compile("\\["+DATE+" "+DATE+"\\]");
	public static final Pattern p_range10 = Pattern.compile("\\]"+DATE+" "+DATE+"\\]");
	public static final Pattern p_range01 = Pattern.compile("\\["+DATE+" "+DATE+"\\[");
	
	private Matcher m;
	
	
	
	private Service toString;
	
	public EntityImpl() throws Exception
	{
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		m = p_equals.matcher(s);
		if(m.matches())
		{
			String value = m.group(1);
			if(value.contains("#")) return new F_mask(value);
			return new F_equals(value);
		}
		
		m = p_inf1.matcher(s);
		if(m.matches())
		{
			String value = m.group(1);
			return new F_inf1(value);
		}
		m = p_inf0.matcher(s);
		if(m.matches())
		{
			String value = m.group(1);
			return new F_inf0(value);
		}
		m = p_sup1.matcher(s);
		if(m.matches())
		{
			String value = m.group(1);
			return new F_sup1(value);
		}
		m = p_sup0.matcher(s);
		if(m.matches())
		{
			String value = m.group(1);
			return new F_sup0(value);
		}
		m = p_range11.matcher(s);
		if(m.matches())
		{
			String v1 = m.group(1);
			String v2 = m.group(2);
			return new F_range11(v1,v2);
		}
		m = p_range00.matcher(s);
		if(m.matches())
		{
			String v1 = m.group(1);
			String v2 = m.group(2);
			return new F_range00(v1,v2);
		}
		m = p_range10.matcher(s);
		if(m.matches())
		{
			String v1 = m.group(1);
			String v2 = m.group(2);
			return new F_range10(v1,v2);
		}
		m = p_range01.matcher(s);
		if(m.matches())
		{
			String v1 = m.group(1);
			String v2 = m.group(2);
			return new F_range01(v1,v2);
		}
		return new F_equals(s);
	}
	
	
	
	
	
	private boolean inf0(String str, String[] n)
	{
		try
		{
			String[] p = str.split("\\.");
			if(p.length!=3) return false;
			
			if(i(p[0])<i(n[0])) return true;
			if(i(p[0])>i(n[0])) return false;
			if(n[1].equals("##")) return true;
			if(n[1].equals("12") && n[2].equals("31") && p[1].equals("##")) return true;
			
			if(i(p[1])<i(n[1])) return true;
			if(i(p[1])>i(n[1])) return false;
			if(n[2].equals("##")) return true;
			if(n[2].equals("31") && p[2].equals("##")) return true;
			
			if(i(p[2])<=i(n[2])) return true;
			return false;
		}
		catch(Exception e){return false;}
	}
	
	
	private boolean inf1(String str, String[] n)
	{
		try
		{
			String[] p = str.split("\\.");
			if(p.length!=3) return false;
			
			if(i(p[0])<i(n[0])) return true;
			if(i(p[0])>i(n[0])) return false;
			
			if(i(p[1])<i(n[1])) return true;
			if(i(p[1])>i(n[1])) return false;
			
			if(i(p[2])<i(n[2])) return true;
			return false;
		}
		catch(Exception e){return false;}
	}
	
	
	private boolean sup0(String str, String[] n)
	{
		try
		{
			String[] p = str.split("\\.");
			if(p.length!=3) return false;
			
			if(i(p[0])>i(n[0])) return true;
			if(i(p[0])<i(n[0])) return false;
			if(n[1].equals("##")) return true;
			if(n[1].equals("01") && n[2].equals("01") && p[1].equals("##")) return true;
			
			if(i(p[1])>i(n[1])) return true;
			if(i(p[1])<i(n[1])) return false;
			if(n[2].equals("##")) return true;
			if(n[2].equals("01") && p[2].equals("##")) return true;
			
			if(i(p[2])>=i(n[2])) return true;
			return false;
		}
		catch(Exception e){return false;}
	}
	
	
	private boolean sup1(String str, String[] n)
	{
		try
		{
			String[] p = str.split("\\.");
			if(p.length!=3) return false;
			
			if(i(p[0])>i(n[0])) return true;
			if(i(p[0])<i(n[0])) return false;
			
			if(i(p[1])>i(n[1])) return true;
			if(i(p[1])<i(n[1])) return false;
			
			if(i(p[2])>i(n[2])) return true;
			return false;
		}
		catch(Exception e){return false;}
	}

	
	
	private boolean range00(String str, String[] n1, String[] n2)
	{return sup0(str,n1) && inf0(str,n2);}
	
	private boolean range11(String str, String[] n1, String[] n2)
	{return sup1(str,n1) && inf1(str,n2);}
	
	private boolean range10(String str, String[] n1, String[] n2)
	{return sup1(str,n1) && inf0(str,n2);}
	
	private boolean range01(String str, String[] n1, String[] n2)
	{return sup0(str,n1) && inf1(str,n2);}

	

	private int i(String s) throws Exception
	{return Integer.parseInt(s);}
	
	
	
	
	
	private class F_mask implements F
	{
		private String value;
		private int valueLength;
		
		public F_mask(String value)
		{
			this.value = value;
			valueLength = value.length();
		}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = formatStr((String) toString.t(obj));
			if(str.length()!=valueLength) return false;
			for(int i=0;i<valueLength;i++)
			{
				char c1 = value.charAt(i);
				char c2 = str.charAt(i);
				if(c1!='#' && c1!=c2) return false;
			}
			return true;
		}
	}

	
	
	
	
	
	
	
	
	
	private class F_equals implements F
	{
		private String value;
		public F_equals(String value)
		{this.value = value;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = formatStr((String) toString.t(obj));
			return str.equals(value);
		}
	}
	
	
	
	private class F_inf0 implements F
	{
		private String[] n;
		public F_inf0(String value)
		{n = value.split("\\.");}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) toString.t(obj);
			if(str.startsWith("<=")) return inf0(formatStr(str.substring(2)),n);
			if(str.startsWith("<")) return inf0(formatStr(str.substring(1)),n);
			return inf0(formatStr(str),n);
		}
	}
	
	
	
	private class F_inf1 implements F
	{
		private String[] n;
		public F_inf1(String value)
		{n = value.split("\\.");}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) toString.t(obj);
			if(str.startsWith("<=")) return inf1(formatStr(str.substring(2)),n);
			if(str.startsWith("<")) return inf0(formatStr(str.substring(1)),n);
			return inf1(formatStr(str),n);
		}
	}
	
	
	
	private class F_sup0 implements F
	{
		private String[] n;
		public F_sup0(String value)
		{n = value.split("\\.");}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) toString.t(obj);
			if(str.startsWith(">=")) return sup0(formatStr(str.substring(2)),n);
			if(str.startsWith(">")) return sup0(formatStr(str.substring(1)),n);
			return sup0(formatStr(str),n);
		}
	}
	
	
	
	private class F_sup1 implements F
	{
		private String[] n;
		public F_sup1(String value)
		{n = value.split("\\.");}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) toString.t(obj);
			if(str.startsWith(">=")) return sup1(formatStr(str.substring(2)),n);
			if(str.startsWith(">")) return sup0(formatStr(str.substring(1)),n);
			return sup1(formatStr(str),n);
		}
	}
	
	
	
	private class F_range00 implements F
	{
		private String[] n1;
		private String[] n2;
		public F_range00(String value1, String value2)
		{
			n1 = value1.split("\\.");
			n2 = value2.split("\\.");
		}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = formatStr((String) toString.t(obj));
			return range00(str,n1,n2);
		}
	}
	
	
	
	private class F_range11 implements F
	{
		private String[] n1;
		private String[] n2;
		public F_range11(String value1, String value2)
		{
			n1 = value1.split("\\.");
			n2 = value2.split("\\.");
		}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = formatStr((String) toString.t(obj));
			return range11(str,n1,n2);
		}
	}
	
	
	
	private class F_range10 implements F
	{
		private String[] n1;
		private String[] n2;
		public F_range10(String value1, String value2)
		{
			n1 = value1.split("\\.");
			n2 = value2.split("\\.");
		}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = formatStr((String) toString.t(obj));
			return range10(str,n1,n2);
		}
	}
	
	
	
	private class F_range01 implements F
	{
		private String[] n1;
		private String[] n2;
		public F_range01(String value1, String value2)
		{
			n1 = value1.split("\\.");
			n2 = value2.split("\\.");
		}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = formatStr((String) toString.t(obj));
			return range01(str,n1,n2);
		}
	}

	
	
	private String formatStr(String s) throws Exception
	{
		if(s.equals("")) return "";
		if(s.contains(".") && s.length()==10) return s;
		if(s.length()==8) return s.substring(0,4)+"."+s.substring(4,6)+"."+s.substring(6,8);
		throw new Exception("Invalid string format for date: ["+s+"]");
	}
}
