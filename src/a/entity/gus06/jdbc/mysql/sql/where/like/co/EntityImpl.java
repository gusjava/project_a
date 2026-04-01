package a.entity.gus06.jdbc.mysql.sql.where.like.co;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231111";}


	private Service formatName;
	private Service formatValue;
	private Service formatForLike;
	
	public EntityImpl() throws Exception
	{
		formatName = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");
		formatValue = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");
		formatForLike = Outside.service(this,"gus06.jdbc.mysql.format.sql.stringforlike");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof Object[]) return fromArray((Object[]) obj);
		if(obj instanceof List) return fromList((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String fromArray(Object[] array) throws Exception
	{
		if(array.length!=2) throw new Exception("Wrong data length: "+array.length);
		
		String name = (String) array[0];
		Object value = array[1];
		
		StringBuffer b = new StringBuffer();
		buildCondition(b, name, value);
		return b.toString();
		
	}
	
	
	private String fromList(List list) throws Exception
	{
		if(list.size()!=2) throw new Exception("Wrong data length: "+list.size());
		
		String name = (String) list.get(0);
		Object value = list.get(1);
		
		StringBuffer b = new StringBuffer();
		buildCondition(b, name, value);
		return b.toString();
		
	}
	
	
	
	
	private void buildCondition(StringBuffer b, String name, Object value) throws Exception
	{
		if(value instanceof String) buildConditionScalar(b, name, value);
		else if(value instanceof Number) buildConditionScalar(b, name, value);
		else if(value instanceof Date) buildConditionScalar(b, name, value);
		else if(value instanceof Boolean) buildConditionScalar(b, name, value);
		
		else if(value instanceof List) buildConditionIn(b, name, (List) value);
		else if(value instanceof Set) buildConditionIn(b, name, new ArrayList((Set) value));
		
		else throw new Exception("Unsupported value type: "+value.getClass());
	}
	
	
	private void buildConditionScalar(StringBuffer b, String name, Object value) throws Exception
	{
		b.append(formatName(name));
		b.append(" LIKE ");
		b.append(formatValue(value));
	}
	
	private void buildConditionIn(StringBuffer b, String name, List list) throws Exception
	{
		if(list.isEmpty()) {b.append("FALSE");return;}
		
		int nb = list.size();
		String name_ = formatName(name);
		for(int i=0;i<nb;i++)
		{
			String value = formatValue(list.get(i));
			
			b.append(name_);
			b.append(" LIKE ");
			b.append(value);
			if(i<nb-1) b.append(" OR ");
		}
	}
	
	
	
	private String formatName(String name) throws Exception
	{return (String) formatName.t(name);}
	
	
	private String formatValue(Object value) throws Exception
	{
		String likeValue = "%"+formatForLike.t(value)+"%";
		return (String) formatValue.t(likeValue);
	}
}
