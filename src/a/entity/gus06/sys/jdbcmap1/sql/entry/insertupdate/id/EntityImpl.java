package a.entity.gus06.sys.jdbcmap1.sql.entry.insertupdate.id;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150806";}

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
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String path = (String) o[0];
		String id = (String) o[1];
		Map map = (Map) o[2];
		
		if(map.isEmpty()) throw new Exception("Insert SQL impossible with empty map");
		if(map.containsKey(PRIMARY_KEY)) throw new Exception(PRIMARY_KEY+" cannot be a field since it is the primary key");
		
		List fields = sortedList(map.keySet());
		
		return "INSERT INTO "+formatName(path)+" "+
			fieldsBlock(fields)+" VALUES "+valuesBlock(fields,map,id)+
			" ON DUPLICATE KEY UPDATE "+updateBlock(map);
	}
	
	
	
	private String fieldsBlock(List fields) throws Exception
	{
		StringBuffer b = new StringBuffer("("+PRIMARY_KEY+",");
		for(int i=0;i<fields.size();i++)
		{
			String field = (String) fields.get(i);
			b.append(formatName(field)+",");
		}
		b.deleteCharAt(b.length()-1);
		return b.toString()+")";
	}
	
	
	private String valuesBlock(List fields, Map map, String id) throws Exception
	{
		StringBuffer b = new StringBuffer("("+formatValue(id)+",");
		for(int i=0;i<fields.size();i++)
		{
			String field = (String) fields.get(i);
			String value = (String) map.get(field);
			b.append(formatValue(value)+",");
		}
		b.deleteCharAt(b.length()-1);
		return b.toString()+")";
	}
	
	
	
	
	private String updateBlock(Map map) throws Exception
	{
		StringBuffer b = new StringBuffer();
		List fields = sortedList(map.keySet());
		for(int i=0;i<fields.size();i++)
		{
			String field = (String) fields.get(i);
			String value = (String) map.get(field);
			b.append(formatName(field)+"="+formatValue(value)+",");
		}
		b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	
	private List sortedList(Set set)
	{
		ArrayList list = new ArrayList(set);
		Collections.sort(list);
		return list;
	}


	private String formatValue(String value) throws Exception
	{return (String) formatValue.t(value);}
	
	private String formatName(String name) throws Exception
	{return (String) formatName.t(name);}
}
