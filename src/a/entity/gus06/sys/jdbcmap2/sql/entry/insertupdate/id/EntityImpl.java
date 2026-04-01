package a.entity.gus06.sys.jdbcmap2.sql.entry.insertupdate.id;

import java.util.Iterator;
import java.util.Map;
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
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String path = (String) o[0];
		String id = (String) o[1];
		Map map = (Map) o[2];
		
		String fieldsBlock = "("+KEY_ID+", "+formatName(KEY_KEY)+", "+formatName(KEY_VALUE)+")";
		String valuesBlock = valuesBlock(id,map);
		
		return "INSERT INTO "+formatName(path)+" "+fieldsBlock+" VALUES "+valuesBlock;
	}
	
	
	
	
	
	private String valuesBlock(String id, Map map) throws Exception
	{
		StringBuffer b = new StringBuffer();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) map.get(key);
			
			String id_ = formatValue(id);
			String key_ = formatValue(key);
			String value_ = formatValue(value);
			
			b.append("("+id_+", "+key_+", "+value_+"),");
		}
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	

	private String formatValue(String value) throws Exception
	{return (String) formatValue.t(value);}
	
	private String formatName(String name) throws Exception
	{return (String) formatName.t(name);}
}
