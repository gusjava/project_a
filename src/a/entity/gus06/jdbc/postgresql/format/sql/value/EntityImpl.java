package a.entity.gus06.jdbc.postgresql.format.sql.value;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190726";}

	public final static int LIMITSIZE = 65535;
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return "null";
		if(obj instanceof String) return formatString((String) obj);
		if(obj instanceof Number) return formatNumber((Number) obj);
		if(obj instanceof Boolean) return formatBoolean((Boolean) obj);
		if(obj instanceof Date) return formatDate((Date) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String formatString(String value)
	{
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
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private String formatDate(Date value)
	{
		return "'"+sdf.format(value)+"'";
	}
}
