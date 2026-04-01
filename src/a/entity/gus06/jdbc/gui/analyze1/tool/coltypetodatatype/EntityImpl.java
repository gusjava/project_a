package a.entity.gus06.jdbc.gui.analyze1.tool.coltypetodatatype;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230227";}
	
	public static final String TYPE_BOOLEAN = "BOOLEAN";
	public static final String TYPE_INTEGER = "INTEGER";
	public static final String TYPE_LONG = "LONG";
	public static final String TYPE_DOUBLE = "DOUBLE";
	public static final String TYPE_DATE = "DATE";
	public static final String TYPE_STRING = "STRING";
	public static final String TYPE_LSTRING = "LSTRING";


	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String colType = (String) obj;
		
		if(isTypeBoolean(colType)) return TYPE_BOOLEAN;
		if(isTypeInteger(colType)) return TYPE_INTEGER;
		if(isTypeLong(colType)) return TYPE_LONG;
		if(isTypeDouble(colType)) return TYPE_DOUBLE;
		if(isTypeDate(colType)) return TYPE_DATE;
		if(isTypeString(colType)) return TYPE_STRING;
		if(isTypeLString(colType)) return TYPE_LSTRING;
		
		throw new Exception("Unsupported col type: "+colType);
	}
	
	private boolean isTypeBoolean(String colType)
	{return isOfTypes(colType,"tinyint");}
	
	private boolean isTypeInteger(String colType)
	{return isOfTypes(colType,"int","smallint");}
	
	private boolean isTypeLong(String colType)
	{return isOfTypes(colType,"bigint");}
	
	private boolean isTypeDouble(String colType)
	{return isOfTypes(colType,"decimal","float","double");}
	
	private boolean isTypeDate(String colType)
	{return isOfTypes(colType,"date","datetime","timestamp");}
	
	private boolean isTypeString(String colType)
	{return isOfTypes(colType,"varchar","char");}
	
	private boolean isTypeLString(String colType)
	{return isOfTypes(colType,"mediumtext","longtext","text");}
	
	
	private boolean isOfTypes(String colType, String... types)
	{
		for(String type : types) if(isOfType(colType,type)) return true;
		return false;
	}
	
	private boolean isOfType(String colType, String type)
	{return colType.equals(type) || colType.startsWith(type+"(");}
}