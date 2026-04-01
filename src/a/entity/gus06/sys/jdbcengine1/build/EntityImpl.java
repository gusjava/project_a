package a.entity.gus06.sys.jdbcengine1.build;

import a.framework.*;
import java.util.Map;
import java.sql.Connection;
import java.io.File;
import java.util.List;
import java.sql.ResultSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161008";}

	public static final String KEY_CX = "cx";
	public static final String KEY_DIR = "dir";
	public static final String KEY_TABLENAME = "tablename";
	public static final String KEY_ORDERBY = "orderby";



	private Service count;
	private Service rsTo;
	private Service executeSql;
	private Service saveNew;
	

	public EntityImpl() throws Exception
	{
		count = Outside.service(this,"gus06.jdbc.mysql.perform.select.count");
		rsTo = Outside.service(this,"gus06.jdbc.resultset.toobjectmaplist");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
		saveNew = Outside.service(this,"gus06.sys.jdbcengine1.savenew");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		Connection cx = (Connection) get1(map,KEY_CX);
		File dir = (File) get1(map,KEY_DIR);
		String tableName = (String) get1(map,KEY_TABLENAME);
		String orderby = (String) get1(map,KEY_ORDERBY);
		
		return new Holder(cx,dir,tableName,orderby);
	}
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
	
	
	
	
	private class Holder implements G
	{
		private Connection cx;
		private File dir;
		private String tableName;
		private String orderby;
		
		public Holder(Connection cx, File dir, String tableName, String orderby)
		{
			this.cx = cx;
			this.dir = dir;
			this.tableName = tableName;
			this.orderby = orderby;
			
			dir.mkdirs();
		}
		
		public Object g() throws Exception
		{
			String nb_ = (String) count.t(new Object[]{cx,tableName});
			int nb = Integer.parseInt(nb_);
			
			int nb0 = dir.list().length;
			
			if(nb0>nb) throw new Exception("Invalid data state inside JDBC engine (row might have been deleted inside "+tableName+")");
			if(nb0==nb) return null;
			
			int limit = nb-nb0;
			
			String sql = "SELECT * FROM `"+tableName+"` ORDER BY `"+orderby+"` DESC LIMIT "+limit;
			ResultSet rs = (ResultSet) executeSql.t(new Object[]{cx,sql});
			List list = (List) rsTo.t(rs);
			
			if(list.size()!=limit) throw new Exception("Unexpected row number have been returned: "+list.size()+" (expected:"+limit+")");
			
			saveNew.p(new Object[]{dir,list});
			return list;
		}
	}
}
