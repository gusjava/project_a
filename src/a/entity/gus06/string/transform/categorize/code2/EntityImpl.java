package a.entity.gus06.string.transform.categorize.code2;

import a.framework.*;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151020";}
	
	public static final Pattern ALPHANUM1 = Pattern.compile("[a-zA-Z0-9_]+");
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		if(s==null) return "0";
		if(s.equals("")) return "1";
		
		Integer n = toInt(s);
		if(n!=null)
		{
			int n_ = n.intValue();
			if(n_==0) return "2";
			if(n>0) return "3";
		}
		
		Double d = toDouble(s);
		if(d!=null) return "4";
		
		String s_ = s.toLowerCase();
		if(s_.equals("true")) return "5";
		if(s_.equals("false")) return "6";
		
		if(ALPHANUM1.matcher(s_).matches()) return "7";
		if(s_.indexOf('\n')==-1 && s_.indexOf('\r')==-1) return "8";
		
		return "9";
	}
	
	
	private Integer toInt(String s)
	{
		try{return Integer.valueOf(s);}
		catch(NumberFormatException e){}
		return null;
	}
	
	private Double toDouble(String s)
	{
		try{return Double.valueOf(s);}
		catch(NumberFormatException e){}
		return null;
	}
}
