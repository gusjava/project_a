package a.entity.gus06.sys.learning1.engine;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Map;
import java.sql.Connection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250707";}


	private Service buildCx;
	private Service performFail;
	private Service performSuccess;
	private Service performNext;
	private Service getProgression;
	private Service getDescription;
	private Service completeConfigMap;

	public EntityImpl() throws Exception
	{
		buildCx = Outside.service(this,"gus06.sys.learning1.engine.cx.build");
		performFail = Outside.service(this,"gus06.sys.learning1.engine.perform.fail");
		performSuccess = Outside.service(this,"gus06.sys.learning1.engine.perform.success");
		performNext = Outside.service(this,"gus06.sys.learning1.engine.perform.next");
		getProgression = Outside.service(this,"gus06.sys.learning1.engine.perform.progression");
		getDescription = Outside.service(this,"gus06.sys.learning1.engine.perform.description");
		completeConfigMap = Outside.service(this,"gus06.sys.learning1.engine.configmap.complete");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Holder((File) obj);
	}
	
	
	private class Holder implements V, R
	{
		private File dbFile;
		private G getCx;
		private Set codes;
		private Map config;
		
		public Holder(File dbFile) throws Exception
		{
			this.dbFile = dbFile;
			getCx = (G) buildCx.t(dbFile);
			config = (Map) completeConfigMap.t(null);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("codes")) {this.codes = (Set) obj;return;}
			if(key.equals("config")) {this.config = (Map) completeConfigMap.t(obj);return;}
			if(key.equals("success")) {handleSuccess((String[]) obj);return;}
			if(key.equals("fail")) {handleFail((String[]) obj);return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("next")) return next();
			if(key.equals("codes")) return codes;
			if(key.equals("dbFile")) return dbFile;
			if(key.equals("getCx")) return getCx;
			if(key.equals("config")) return config;
			if(key.equals("defaultConfig")) return completeConfigMap.g();
			if(key.equals("progression")) return progression();
			if(key.equals("description")) return description();
			
			if(key.equals("keys")) return new String[]{
				"next","codes","dbFile","getCx",
				"config","defaultConfig",
				"progression","description"};
			throw new Exception("Unknown key: "+key);
		}
		
		
		private String next() throws Exception
		{
			Connection cx = (Connection) getCx.g();
			return (String) performNext.t(new Object[]{config, cx, codes});
		}
		
		
		private void handleSuccess(String[] infos) throws Exception
		{
			Connection cx = (Connection) getCx.g();
			performSuccess.p(new Object[]{config, cx, infos[0], infos[1]});
		}
		
		private void handleFail(String[] infos) throws Exception
		{
			Connection cx = (Connection) getCx.g();
			performFail.p(new Object[]{config, cx, infos[0], infos[1], infos[2]});
		}
		
		private Integer progression() throws Exception
		{
			Connection cx = (Connection) getCx.g();
			return (Integer) getProgression.t(cx);
		}
		
		private String description() throws Exception
		{
			Connection cx = (Connection) getCx.g();
			return (String) getDescription.t(cx);
		}
	}
}