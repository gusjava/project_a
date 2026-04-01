package a.entity.gus06.jdbc.mysql.sql.where.and;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231016";}


	private Service buildWhere;
	
	public EntityImpl() throws Exception
	{
		buildWhere = Outside.service(this,"gus06.jdbc.mysql.sql.where");
	}

	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof List) return fromList((List) obj);
		if(obj instanceof Map) return fromMap((Map) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String fromList(List list) throws Exception
	{
		StringBuffer b = new StringBuffer();
		int nb = list.size();
		for(int i=0;i<nb;i++)
		{
			String where = (String) buildWhere.t(list.get(i));
			b.append("(");
			b.append(where);
			b.append(")");
			
			if(i<nb-1) b.append(" AND ");
		}
		return b.toString();
	}
	
	
	private String fromMap(Map map) throws Exception
	{
		StringBuffer b = new StringBuffer();
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String name = (String) it.next();
			Object value = map.get(name);
			String where = (String) buildWhere.t(new Object[]{name,value});
			
			if(b.length()>0) b.append(" AND ");
			b.append(where);
		}
		return b.toString();
	}
}
