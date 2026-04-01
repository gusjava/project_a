package a.entity.gus06.jdbc.mysql.format.sql.value;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141009";}


	public final static int LIMITSIZE = 65535;
	
	
	public Object t(Object obj) throws Exception
	{return formatValue(obj);}
	
	
	private String formatValue(Object value) throws Exception
	{
		if(value==null) return "null";
		if(value instanceof List) return formatList((List) value);
		if(value instanceof Set) return formatList(new ArrayList((Set) value));
		if(value instanceof String) return formatString((String) value);
		if(value instanceof Number) return formatNumber((Number) value);
		if(value instanceof Boolean) return formatBoolean((Boolean) value);
		if(value instanceof Date) return formatDate((Date) value);
		
		throw new Exception("Invalid data type: "+value.getClass().getName());
	}
	
	
	private String formatList(List value) throws Exception
	{
		StringBuffer b = new StringBuffer();
		b.append("(");
		int nb = value.size();
		for(int i=0;i<nb;i++)
		{
			b.append(formatValue(value.get(i)));
			if(i<nb-1) b.append(", ");
		}
		b.append(")");
		return b.toString();
	}
	
	
	private String formatString(String value)
	{
		if(value.equals("NULL")) return "null";
		if(value.matches("&+NULL")) value = value.substring(1);
		
		if(value.length()>LIMITSIZE) value = value.substring(0,LIMITSIZE);
		return "'"+value.replace("\\","\\\\").replace("'","\\'")+"'";
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
		return "'"+sdf.format(value)+"'";
	}
}