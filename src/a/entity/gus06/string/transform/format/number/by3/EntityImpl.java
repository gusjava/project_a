package a.entity.gus06.string.transform.format.number.by3;

import a.framework.*;
import java.text.NumberFormat;
import java.util.Locale;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161117";}


	private NumberFormat nf;

	public EntityImpl() throws Exception
	{nf = NumberFormat.getNumberInstance(Locale.FRENCH);}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String) return format(""+obj);
		if(obj instanceof Integer) return format(""+obj);
		if(obj instanceof Long) return format(""+obj);
		
		if(obj instanceof Object[]) return format((Object[]) obj);
		if(obj instanceof int[]) return format((int[]) obj);
		if(obj instanceof long[]) return format((long[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String format(String s)
	{
		long d = Long.parseLong(s);
		return nf.format(d);
	}
	
	private String[] format(Object[] s)
	{
		int len = s.length;
		String[] t = new String[len];
		for(int i=0;i<len;i++) t[i] = format(""+s[i]);
		return t;
	}
	
	private String[] format(int[] s)
	{
		int len = s.length;
		String[] t = new String[len];
		for(int i=0;i<len;i++) t[i] = format(""+s[i]);
		return t;
	}
	
	private String[] format(long[] s)
	{
		int len = s.length;
		String[] t = new String[len];
		for(int i=0;i<len;i++) t[i] = format(""+s[i]);
		return t;
	}
}
