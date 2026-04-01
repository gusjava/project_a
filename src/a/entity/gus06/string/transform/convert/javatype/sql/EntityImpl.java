package a.entity.gus06.string.transform.convert.javatype.sql;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220525";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = toString(obj).toUpperCase().trim();
		
		boolean notNull = s.endsWith("*");
		if(notNull) s = s.substring(0,s.length()-1);
		
		String sql = convert(s);
		if(notNull) sql = sql+" NOT NULL";
		return sql;
	}
	
	
	private String convert(String s) throws Exception
	{
		//DATETIME
		if(s.equals("DATETIME")) return "DATETIME";
		if(s.equals("LOCALDATETIME")) return "DATETIME";
		if(s.equals("DATE")) return "DATETIME";
		
		//TINYINT(1)
		if(s.equals("TINYINT(1)")) return "TINYINT(1)";
		if(s.equals("BOOLEAN")) return "TINYINT(1)";
		
		//INT(4)
		if(s.equals("INT(4)")) return "INT(4)";
		if(s.equals("INT")) return "INT(4)";
		if(s.equals("INTEGER")) return "INT(4)";
		
		//BIGINT(20)
		if(s.equals("BIGINT(20)")) return "BIGINT(20)";
		if(s.equals("LONG")) return "BIGINT(20)";
		
		//DECIMAL(15,2)
		if(s.equals("DECIMAL(15,2)")) return "DECIMAL(15,2)";
		if(s.equals("DOUBLE")) return "DECIMAL(15,2)";
		if(s.equals("FLOAT")) return "DECIMAL(15,2)";
		
		//VARCHAR(500)
		if(s.equals("VARCHAR(500)")) return "VARCHAR(500)";
		if(s.equals("STRING")) return "VARCHAR(500)";
		
		//TEXT
		if(s.equals("TEXT")) return "TEXT";
		if(s.equals("STRING(-1)")) return "TEXT";
		
		if(s.matches("INT\\([0-9]+\\)")) return s;
		if(s.matches("BIGINT\\([0-9]+\\)")) return s;
		if(s.matches("DECIMAL\\([0-9]+,[0-9]+\\)")) return s;
		if(s.matches("VARCHAR\\([0-9]+\\)")) return s;
		if(s.matches("STRING\\([0-9]+\\)")) return s.replace("STRING","VARCHAR");
		
		throw new Exception("Unsupported Java type: "+s);
	}
	
	
	private String toString(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof Class) return ((Class) obj).getSimpleName();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}