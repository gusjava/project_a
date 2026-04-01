package a.entity.gus06.string.transform.convert.sqltype.java;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220525";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = toString(obj).toUpperCase();
		
		s = s.replace("*","").trim();
		
		if(s.equals("DATETIME")) return "Date";
		if(s.equals("TINYINT(1)")) return "Boolean";
		if(s.equals("INT(4)")) return "Integer";
		if(s.equals("BIGINT(20)")) return "Long";
		if(s.matches("DECIMAL\\([^\\)]+\\)")) return "Double";
		if(s.matches("VARCHAR\\([^\\)]+\\)")) return "String";
		if(s.equals("TEXT")) return "String";
		
		s = s.replaceAll("\\([^\\)]+\\)","");
		
		if(s.equals("DATE")) return "Date";
		if(s.equals("BOOLEAN")) return "Boolean";
		if(s.equals("INT")) return "Integer";
		if(s.equals("INTEGER")) return "Integer";
		if(s.equals("LONG")) return "Long";
		if(s.equals("DOUBLE")) return "Double";
		if(s.equals("FLOAT")) return "Float";
		if(s.equals("STRING")) return "String";
		
		throw new Exception("Unsupported Sql type: "+s);
	}
	
	private String toString(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}