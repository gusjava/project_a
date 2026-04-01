package a.entity.gus06.sys.jdbcmigration1.engine;

import a.framework.*;
import java.util.Map;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.List;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170515";}
	
	public static final String KEY_CX = "cx";
	public static final String KEY_DB = "db";
	public static final String KEY_TABLE = "table";
	public static final String KEY_WHERE = "where";
	public static final String KEY_FKMAP = "fkmap";


	private Service selectWhere;
	private Service insertRow;
	private Service buildFkMap;
	private Service buildPkMap;

	private PrintStream p;

	public EntityImpl() throws Exception
	{
		selectWhere = Outside.service(this,"gus06.jdbc.mysql.perform.select.all.where.as.maplist");
		insertRow = Outside.service(this,"gus06.jdbc.mysql.perform.row.insert");
		buildFkMap = Outside.service(this,"gus06.jdbc.mysql.perform.fk.map1.db");
		buildPkMap = Outside.service(this,"gus06.jdbc.mysql.perform.pk.map1.db");
		
		p = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map input = (Map) o[0];
		Map output = (Map) o[1];
		
		return new Holder(input,output);
	}
	
	
	
	
	private class Holder implements E
	{
		private Connection cx1;
		private String db1;
		private String table1;
		private String where1;
		
		private Connection cx2;
		private String db2;
		
		private Map fkMap;
		private Map pkMap;
		
		private Map mapping;
		private Map pending;
		private Map done;
		
		
		public Holder(Map input, Map output) throws Exception
		{
			mapping = new HashMap();
			pending = new HashMap();
			done = new HashMap();
			
			cx1 = (Connection) get1(input,KEY_CX);
			db1 = (String) get1(input,KEY_DB);
			table1 = (String) get1(input,KEY_TABLE);
			where1 = (String) get1(input,KEY_WHERE);
			
			cx2 = (Connection) get0(output,KEY_CX,cx1);
			db2 = (String) get0(output,KEY_DB,db1);
			
			fkMap = (Map) buildFkMap.t(new Object[]{cx1,db1});
			pkMap = (Map) buildPkMap.t(new Object[]{cx1,db1});
			
			Map fkMap0 = (Map) get0(input,KEY_FKMAP);
			if(fkMap0!=null) completeMap(fkMap,fkMap0);
		}
		
		
		
		public void e() throws Exception
		{
			p.println("starting migration");
			
			initOutputDb();
			initPending();
			
			while(!pending.isEmpty())
			{
				String key = (String) pending.keySet().iterator().next();
				Map data = (Map) pending.get(key);
				
				migrateData(key,data);
				
				done.put(key,data);
				pending.remove(key);
			}
		}
		
		
		private void initOutputDb()
		{
			if(cx1==cx2 && db1.equals(db2)) return;
			
			//il faut cr�er db2 s'il n'existe pas encore
			//il faut dupliquer la structure de db1 vers db2 pour chaque table de db1
		}
		
		
		
		
		private void initPending() throws Exception
		{
			String path1 = db1+"."+table1;
			List l = (List) selectWhere.t(new Object[]{cx1,path1,where1});
			String pk = (String) get1(pkMap,table1);
			int size = l.size();
			
			p.println("path: "+path1);
			p.println("data number: "+size);
			
			for(int i=0;i<size;i++)
			{
				Map data = (Map) l.get(i);
				String id = (String) data.remove(pk);
				String key = table1+":"+id;
				pending.put(key,data);
			}
		}
		
		
		
		
		
		private void migrateData(String key, Map data) throws Exception
		{
			String table = key.split(":")[0];
			String path = db2+"."+table;
			String newId = (String) insertRow.t(new Object[]{cx2,path,data});
			mapping.put(key,newId);
		}
	}
	
	
	
	
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return map.get(key);
	}
	
	private Object get0(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private Object get0(Map map, String key, Object defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return map.get(key);
	}
	
	
	
	private void completeMap(Map map1, Map map2) throws Exception
	{
		Iterator it = map2.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = map2.get(key);
			
			if(map1.containsKey(key))
			{
				if(!map1.get(key).equals(value)) throw new Exception("Invalid fk rule: "+key+"="+value);
			}
			else map1.put(key,value);
		}
	}
}
