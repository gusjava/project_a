package a.entity.gus06.sys.jdbcmap2.sql.entry.select.id;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150809";}

	public final static String KEY_ID = "ID";
	public final static String KEY_KEY = "KEY";
	public final static String KEY_VALUE = "VALUE";

	
	private Service formatName;
	private Service formatValue;
	
	
	public EntityImpl() throws Exception
	{
		formatName = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");
		formatValue = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");
	}
	


	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String path = o[0];
		String id = o[1];
		
		return "SELECT "+formatName(KEY_KEY)+", "+formatName(KEY_VALUE)+" FROM "+formatName(path)+
				" WHERE "+KEY_ID+"="+formatValue(id);
	}


	
	private String formatValue(String value) throws Exception
	{return (String) formatValue.t(value);}
	
	private String formatName(String name) throws Exception
	{return (String) formatName.t(name);}
}
