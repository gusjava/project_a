package a.entity.gus06.jdbc.mysql.sql.where;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170202";}


	private Service formatName;
	private Service formatValue;
	
	public EntityImpl() throws Exception
	{
		formatName = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");
		formatValue = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof String) return obj;
		if(obj instanceof Object[]) return fromArray((Object[]) obj);
		if(obj instanceof List) return fromList((List) obj);
		if(obj instanceof Map) return fromMap((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String fromArray(Object[] array) throws Exception
	{
		if(array.length==2)
		{
			String name = (String) array[0];
			Object value = array[1];
			
			StringBuffer b = new StringBuffer();
			buildCondition(b, name, value);
			return b.toString();
		}
		if(array.length==3)
		{
			String name = (String) array[0];
			String operator = (String) array[1];
			Object value = array[2];
			
			StringBuffer b = new StringBuffer();
			buildCondition(b, name, operator, value);
			return b.toString();
		}
		throw new Exception("Wrong data length: "+array.length);
	}
	
	
	private String fromList(List list) throws Exception
	{
		if(list.size()==2)
		{
			String name = (String) list.get(0);
			Object value = list.get(1);
			
			StringBuffer b = new StringBuffer();
			buildCondition(b, name, value);
			return b.toString();
		}
		if(list.size()==3)
		{
			String name = (String) list.get(0);
			String operator = (String) list.get(1);
			Object value = list.get(2);
			
			StringBuffer b = new StringBuffer();
			buildCondition(b, name, operator, value);
			return b.toString();
		}
		throw new Exception("Wrong data length: "+list.size());
	}
	
	
	private String fromMap(Map map) throws Exception
	{
		StringBuffer b = new StringBuffer();
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String name = (String) it.next();
			Object value = map.get(name);
			
			if(b.length()>0) b.append(" AND ");
			buildCondition(b, name, value);
		}
		return b.toString();
	}
	
	
	private void buildCondition(StringBuffer b, String name, Object value) throws Exception
	{
		if(value==null) buildConditionNull(b, name);
		else if(value instanceof String) buildConditionEq(b, name, value);
		else if(value instanceof Number) buildConditionEq(b, name, value);
		else if(value instanceof Date) buildConditionEq(b, name, value);
		else if(value instanceof Boolean) buildConditionEq(b, name, value);
		
		else if(value instanceof List) buildConditionIn(b, name, (List) value);
		else if(value instanceof Set) buildConditionIn(b, name, new ArrayList((Set) value));
		
		else throw new Exception("Unsupported value type: "+value.getClass());
	}
	
	
	private void buildCondition(StringBuffer b, String name, String operator, Object value) throws Exception
	{
		b.append(formatName(name));
		b.append(" ");
		b.append(operator);
		b.append(" ");
		b.append(formatValue(value));
	}
	
	
	private void buildConditionNull(StringBuffer b, String name) throws Exception
	{
		b.append(formatName(name));
		b.append(" IS NULL");
	}
	
	private void buildConditionEq(StringBuffer b, String name, Object value) throws Exception
	{
		b.append(formatName(name));
		b.append(" = ");
		b.append(formatValue(value));
	}
	
	private void buildConditionIn(StringBuffer b, String name, List value) throws Exception
	{
		if(value.isEmpty()) b.append("FALSE");
		else
		{
			b.append(formatName(name));
			b.append(" IN ");
			b.append(formatValue(value));
		}
	}
	
	
	
	private String formatName(String name) throws Exception
	{return (String) formatName.t(name);}
	
	private String formatValue(Object value) throws Exception
	{return (String) formatValue.t(value);}
}