package a.entity.gus06.sys.jdbcmeta1.initialize;

import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170801";}


	private Service executeSql;
	
	public EntityImpl() throws Exception
	{
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String dbName = (String) o[1];
		
		StringBuffer b = new StringBuffer();
		b.append("CREATE TABLE IF NOT EXISTS attribute_type");
		b.append(" (`id` bigint(20) NOT NULL auto_increment,");
		b.append("`name` VARCHAR(100) DEFAULT '',");
		b.append("`description` VARCHAR(500) DEFAULT '',");
		b.append("`date_creation` DATETIME NOT NULL,");
		b.append("`data_type` ENUM('string1','string2','int','float','date','link','file'),");
		b.append("`entity_type_id` bigint(20) NULL,");
		b.append("`constraint_rule` VARCHAR(500) DEFAULT '',");
		b.append("PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8");
		
		execute(cx,b);
		
		b = new StringBuffer();
		b.append("CREATE TABLE IF NOT EXISTS attribute (");
		b.append("`id` bigint(20) NOT NULL auto_increment,");
		b.append("`name` VARCHAR(100) DEFAULT '',");
		b.append("`description` VARCHAR(500) DEFAULT '',");
		b.append("`date_creation` DATETIME NOT NULL,");
		b.append("`attribute_type_id` bigint(20) NOT NULL,");
		b.append("`entity_type_id` bigint(20) NOT NULL,");
		b.append("PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8");
		
		execute(cx,b);
		
		b = new StringBuffer();
		b.append("CREATE TABLE IF NOT EXISTS entity_type (");
		b.append("`id` bigint(20) NOT NULL auto_increment,");
		b.append("`name` VARCHAR(100) DEFAULT '',");
		b.append("`description` VARCHAR(500) DEFAULT '',");
		b.append("`date_creation` DATETIME NOT NULL,");
		b.append("`parent_id` bigint(20),");
		b.append("PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8");
		
		execute(cx,b);
		
		b = new StringBuffer();
		b.append("CREATE TABLE IF NOT EXISTS entity (");
		b.append("`id` bigint(20) NOT NULL auto_increment,");
		b.append("`name` VARCHAR(100) DEFAULT '',");
		b.append("`description` VARCHAR(500) DEFAULT '',");
		b.append("`date_creation` DATETIME NOT NULL,");
		b.append("`entity_type_id` bigint(20) NOT NULL,");
		b.append("PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8");
		
		execute(cx,b);
	}
	
	
	private void execute(Connection cx, StringBuffer b) throws Exception
	{executeSql.p(new Object[]{cx,b.toString()});}
}
