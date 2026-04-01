package a.entity.gus06.jdbc.mysql.sql.info.counttable.bydb.selection;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231217";}
	

	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");}
	
	
	public Object t(Object obj) throws Exception
	{
		List selection = (List) obj;
		if(selection==null || selection.isEmpty()) return null;
		
		StringBuffer b = new StringBuffer();
		b.append("SELECT TABLE_SCHEMA, COUNT(TABLE_SCHEMA) as count ");
		b.append("FROM information_schema.TABLES WHERE TABLE_SCHEMA IN (");
		
		int nb = selection.size();
		for(int i=0;i<nb;i++)
		{
			String name = (String) selection.get(i);
			b.append(format(name));
			if(i<nb-1) b.append(",");
		}
		
		b.append(") GROUP BY TABLE_SCHEMA");
		return b.toString();
	}

	private String format(String s) throws Exception
	{return (String) format.t(s);}
}