package a.entity.gus06.sys.jwpce1.engine;

import a.framework.*;
import java.io.File;
import java.sql.Connection;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250722";}


	private Service buildCx;
	private Service importer1;
	private Service findAllWhere;
	private Service count;
	private Service formatSql;
	private Service buildQuery;

	public EntityImpl() throws Exception
	{
		buildCx = Outside.service(this,"gus06.sys.jwpce1.engine.cx.build");
		importer1 = Outside.service(this,"gus06.sys.jwpce1.importer1");
		findAllWhere = Outside.service(this,"gus06.sys.jwpce1.engine.cx.findallwhere.edict");
		count = Outside.service(this,"gus06.sys.jwpce1.engine.cx.count.edict");
		formatSql = Outside.service(this,"gus06.jdbc.mysql.format.sql.value");
		buildQuery = Outside.service(this,"gus06.sys.jwpce1.engine.buildquery");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((File) obj);}
	
	
	private class Holder implements V, R
	{
		private File dbFile;
		private G getCx;
		private G getProgress;
		
		public Holder(File dbFile) throws Exception
		{
			this.dbFile = dbFile;
			getCx = (G) buildCx.t(dbFile);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("getProgress")) {getProgress = (G) obj;return;}
			if(key.equals("import")) {importEdict((File) obj);return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("dbFile")) return dbFile;
			if(key.equals("getCx")) return getCx;
			if(key.equals("count")) return count();
			
			if(key.startsWith("where:")) return where(key.substring(6));
			if(key.startsWith("romaji:")) return whereRomaji(key.substring(7));
			if(key.startsWith("kana:")) return whereKana(key.substring(5));
			if(key.startsWith("kanji:")) return whereKanji(key.substring(6));
			if(key.startsWith("query:")) return whereQuery(key.substring(6));
			
			if(key.equals("keys")) return new String[]{"dbFile","getCx","count"};
			throw new Exception("Unknown key: "+key);
		}
		
		private void importEdict(File edict) throws Exception
		{
			Connection cx = (Connection) getCx.g();
			if(getProgress!=null)
				importer1.p(new Object[]{edict, cx, getProgress.g()});
			else importer1.p(new Object[]{edict, cx});
		}
		
		private Long count() throws Exception
		{
			Connection cx = (Connection) getCx.g();
			Long r = (Long) count.t(cx);
			cx.close();
			return r;
		}
		
		private List where(String sql) throws Exception
		{
			Connection cx = (Connection) getCx.g();
			List results = (List) findAllWhere.t(new Object[]{cx,sql});
			cx.close();
			return results;
		}
		
		private List whereQuery(String value) throws Exception
		{return where((String) buildQuery.t(value));}
		
		private List whereRomaji(String value) throws Exception
		{return where("romaji="+formatValue(value));}
		
		private List whereKana(String value) throws Exception
		{return where("kana="+formatValue(value));}
		
		private List whereKanji(String value) throws Exception
		{return where("kanji="+formatValue(value));}
	}
	
		
	private String formatValue(String value) throws Exception
	{return (String) formatSql.t(value);}
}