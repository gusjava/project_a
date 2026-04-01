package a.entity.gus06.jdbc.mysql.format.sql.stringforlike;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231111";}


	public final static int LIMITSIZE = 65535;
	
	
	public Object t(Object obj) throws Exception
	{return formatValue(obj);}
	
	
	private String formatValue(Object value) throws Exception
	{
		if(value==null) return "null";
		if(value instanceof String) return formatString((String) value);
		if(value instanceof Number) return formatNumber((Number) value);
		if(value instanceof Boolean) return formatBoolean((Boolean) value);
		if(value instanceof Date) return formatDate((Date) value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	
	private String formatString(String value)
	{
		if(value.length()>LIMITSIZE) value = value.substring(0,LIMITSIZE);
		return escape(value,'%','_');
	}
	
	private String formatNumber(Number value)
	{
		return value.toString();
	}
	
	private String formatBoolean(Boolean value)
	{
		return value.toString();
	}
	
	private String formatDate(Date value)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(value);
	}
	
	
	
	public static final char ESCAP = '\\';
	
	private String escape(String s, char... kk)
	{
		StringBuilder b = new StringBuilder();
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(c==ESCAP || isChar(c,kk)) b.append(ESCAP);
			b.append(c);
		}
		return b.toString();
	}
	
	private boolean isChar(char c, char... kk)
	{
		for(char k:kk) if(c==k) return true;
		return false;
	}
	
}