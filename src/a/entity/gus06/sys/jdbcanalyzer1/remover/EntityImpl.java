package a.entity.gus06.sys.jdbcanalyzer1.remover;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170522";}


	private Service selectWhere;

	public EntityImpl() throws Exception
	{
		selectWhere = Outside.service(this,"gus06.jdbc.mysql.perform.select.all.where.as.maplist");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Holder((R) obj);
	}
	
	
	private class Holder implements R
	{
		private R engine;
		public Holder(R engine) {this.engine = engine;}
		
		public Object r(String key) throws Exception
		{
			if(key.contains(":"))
			{
				String[] n = key.split(":",2);
				return findData(n[0],n[1]);
			}
			if(key.startsWith("@"))
			{
				return findLinks(key.substring(1));
			}
			return findTables(key);
		}
		
		private Map findData(String inputTable, String inputId) throws Exception
		{
			Connection cx = (Connection) engine.r("cx");
			String db = (String) engine.r("db");
			
			Map m = new HashMap();
			List links = findLinks(inputTable);
			for(int i=0;i<links.size();i++)
			{
				String link = (String) links.get(i);
				String[] n = link.split("@",2);
				String targetTable = n[0];
				String targetColumn = n[1];
				String targetPath = db+"."+targetTable;
				String where = targetColumn+"="+inputId;
				
				List rows = (List) selectWhere.t(new Object[]{cx,targetPath,where});
				m.put(targetTable,rows);
			}
			return m;
		}
		
		private List findLinks(String input) throws Exception
		{
			Map fkmap = (Map) engine.r("fkmap");
			
			List l = new ArrayList();
			Iterator it = fkmap.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				String value = (String) fkmap.get(key);
				
				if(value.equals(input)) l.add(key);
			}
			return l;
		}
		
		
		private List findTables(String input) throws Exception
		{
			Map fkmap = (Map) engine.r("fkmap");
			
			List l = new ArrayList();
			Iterator it = fkmap.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				String value = (String) fkmap.get(key);
				
				if(value.equals(input)) l.add(key.split("@")[0]);
			}
			return l;
		}
	}
}
