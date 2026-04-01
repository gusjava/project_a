package a.entity.gus06.sys.jdbcanalyzer1.engine;

import a.framework.*;
import java.util.Map;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170516";}
	

	private Service findTables;
	private Service buildFkMap1;
	private Service buildPkMap1;
	private Service buildLinkedTo;
	private Service buildRemover;
	private Service buildCheck;
	private Service buildFkMap;

	private PrintStream p;

	public EntityImpl() throws Exception
	{
		findTables = Outside.service(this,"gus06.jdbc.generic.perform.find.tableset.db");
		buildFkMap1 = Outside.service(this,"gus06.jdbc.mysql.perform.fk.map1.db");
		buildPkMap1 = Outside.service(this,"gus06.jdbc.mysql.perform.pk.map1.db");
		buildLinkedTo = Outside.service(this,"gus06.sys.jdbcanalyzer1.linkedto");
		buildRemover = Outside.service(this,"gus06.sys.jdbcanalyzer1.remover");
		buildCheck = Outside.service(this,"gus06.sys.jdbcanalyzer1.check");
		buildFkMap = Outside.service(this,"gus06.sys.jdbcanalyzer1.fkmap");
		
		p = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String db = (String) o[1];
		
		return new Holder(cx,db);
	}
	
	
	
	
	private class Holder implements V, R
	{
		private Connection cx;
		private String db;
		private Set tables;
		
		private Map pkmap1;
		private Map fkmap1;
		private Map fkmap0;
		
		private Object linkedto;
		private Object remover;
		
		public Holder(Connection cx, String db) throws Exception
		{
			this.cx = cx;
			this.db = db;
			
			tables = (Set) findTables.t(new Object[]{cx,db});
			pkmap1 = (Map) buildPkMap1.t(new Object[]{cx,db});
			fkmap1 = (Map) buildFkMap1.t(new Object[]{cx,db});
			
			linkedto = buildLinkedTo.t(this);
			remover = buildRemover.t(this);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("cx")) return cx;
			if(key.equals("db")) return db;
			if(key.equals("tableset")) return tables;
			if(key.equals("tablelist")) return tablelist();
			
			if(key.equals("linkedto")) return linkedto;
			if(key.equals("remover")) return remover;
			if(key.equals("check")) return check();
			
			if(key.equals("pkmap1")) return pkmap1;
			if(key.equals("fkmap1")) return fkmap1;
			if(key.equals("fkmap0")) return fkmap0;
			if(key.equals("fkmap")) return fkmap();
		
			if(key.equals("keys")) return new String[]{"cx","db","tableset","tablelist",
							"linkedto","remover","check",
							"pkmap1","fkmap1","fkmap0","fkmap"};
							
			throw new Exception("Unknown key: "+key);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("fkmap0")) {fkmap0 = (Map) obj;return;}
		
			throw new Exception("Unknown key: "+key);
		}
		
		
		private Object check() throws Exception
		{return buildCheck.t(this);}
		
		
		private List tablelist()
		{
			List l = new ArrayList(tables);
			Collections.sort(l);
			return l;
		}
		
		private Map fkmap() throws Exception
		{return (Map) buildFkMap.t(new Object[]{fkmap0,fkmap1});}
	}
}
