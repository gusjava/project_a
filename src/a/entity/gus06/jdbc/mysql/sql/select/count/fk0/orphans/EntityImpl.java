package a.entity.gus06.jdbc.mysql.sql.select.count.fk0.orphans;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20230304";}
	

	private Service format;
	
	public EntityImpl() throws Exception
	{format = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");}
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
		
		String dbName = o[0];
		String tableName = o[1];
		String colName = o[2];
		String refTable = o[3];
		String refCol = o[4];
		
		return "SELECT COUNT(*) FROM "+format(dbName)+"."+format(tableName)
			+" WHERE "+format(colName)+" IS NOT NULL"
			+" AND  "+format(colName)+" NOT IN "
			+"(SELECT "+format(refCol)+" FROM "+format(dbName)+"."+format(refTable)+")";
	}

	private String format(String s) throws Exception
	{return (String) format.t(s);}
}