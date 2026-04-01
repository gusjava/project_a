package a.entity.gus06.jdbc.gui.analyze1.tool.datatypetocolor;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230227";}
	
	public static final String TYPE_BOOLEAN = "BOOLEAN";
	public static final String TYPE_INTEGER = "INTEGER";
	public static final String TYPE_LONG = "LONG";
	public static final String TYPE_DOUBLE = "DOUBLE";
	public static final String TYPE_DATE = "DATE";
	public static final String TYPE_STRING = "STRING";
	public static final String TYPE_LSTRING = "LSTRING";
	
	public static final Color COLOR_BOOLEAN = Color.ORANGE;
	public static final Color COLOR_INTEGER = new Color(102,204,255);
	public static final Color COLOR_DOUBLE = Color.BLUE;
	public static final Color COLOR_LONG = Color.MAGENTA.darker();
	public static final Color COLOR_DATE = Color.RED;
	public static final Color COLOR_STRING = new Color(153,204,0);
	public static final Color COLOR_LSTRING = Color.GREEN.darker();


	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return obj;
		String type = (String) obj;
		
		if(type.equals(TYPE_BOOLEAN)) return COLOR_BOOLEAN;
		if(type.equals(TYPE_INTEGER)) return COLOR_INTEGER;
		if(type.equals(TYPE_DOUBLE)) return COLOR_DOUBLE;
		if(type.equals(TYPE_LONG)) return COLOR_LONG;
		if(type.equals(TYPE_DATE)) return COLOR_DATE;
		if(type.equals(TYPE_STRING)) return COLOR_STRING;
		if(type.equals(TYPE_LSTRING)) return COLOR_LSTRING;
		
		throw new Exception("Unsupported data type: "+type);
	}
}
