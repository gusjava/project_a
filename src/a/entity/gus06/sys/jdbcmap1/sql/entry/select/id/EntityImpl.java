package a.entity.gus06.sys.jdbcmap1.sql.entry.select.id;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150804";}

	public final static String PRIMARY_KEY = "ID";

	
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
		
		return "SELECT * FROM "+formatName(path)+" WHERE "+PRIMARY_KEY+"="+formatValue(id);
	}


	private String formatValue(String value) throws Exception
	{return (String) formatValue.t(value);}
	
	private String formatName(String name) throws Exception
	{return (String) formatName.t(name);}
}
