package a.entity.gus06.jdbc.postgresql.sql.table.rename;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190719";}



	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.postgresql.format.sql.name");}



	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String path1 = o[0];
		String path2 = o[1];
		
		if(!path2.contains("."))
			return "ALTER TABLE "+format(path1)+" RENAME TO "+format(path2);
		
		String[] nn1 = path1.split("\\.");
		String[] nn2 = path2.split("\\.");
		
		if(nn1.length!=2) throw new Exception("Invalid table path: "+path1);
		if(nn2.length!=2) throw new Exception("Invalid table path: "+path2);
		
		if(!nn1[0].equals(nn2[0])) 
		throw new Exception("Attempt to rename table from different databases: "+nn1[0]+" & "+nn2[0]);
		
		String table2 = nn2[1];
		
		return "ALTER TABLE "+format(path1)+" RENAME TO "+format(table2);
	}

	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
