package a.entity.gus06.appli.gusappmonitor.manager;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.Date;

public class EntityImpl extends S1 implements Entity, T, G, R {

	public String creationDate() {return "20190408";}


	public static final String EVENT = "EVENT";
	public static final String AWT = "AWT";
	public static final String DEBUG = "DEBUG";
	
	public static final String INFO_BUILD_ID = "build_id";
	
	public static final String PROP_WARNPOPUP_ONLOST = "warnpopup_onlost";
	
	public static final String PROP_RESTART_ONLOST = "restart_onlost";
	public static final String PROP_RESTART_ONCLOSED = "restart_onclosed";
	
	public static final String STATE_CONNECTED = "connected";
	public static final String STATE_CLOSED = "closed";
	public static final String STATE_LOST = "lost";
	
	
	
	private Service consoleGui;
	private Service executeStart;
	private Service decodeMap;
	private Service readProp;
	private Service writeProp;
	private Service logger;
	private Service warnPopup;
	
	private Map m;
	private Object lastest;
	
	private File rootDir;
	
	
	public EntityImpl() throws Exception
	{
		consoleGui = Outside.service(this,"gus06.appli.gusappmonitor.gui.console");
		executeStart = Outside.service(this,"gus06.appli.gusappmonitor.execute.app.start");
		decodeMap = Outside.service(this,"gus06.map.string.stringtomap.builder3.urldecoding");
		readProp = Outside.service(this,"gus.x.file.prop.read");
		writeProp = Outside.service(this,"gus06.file.write.properties");
		logger = Outside.service(this,"gus06.appli.gusappmonitor.logger");
		warnPopup = Outside.service(this,"gus06.appli.gusappmonitor.tool.warnpopup");
		
		rootDir = (File) Outside.resource(this,"defaultdir");
		m = new HashMap();
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String initString = (String) o[0];
		P sender = (P) o[1];
		
		Map infoMap = (Map) decodeMap.t(initString);
		String buildId = (String) infoMap.get(INFO_BUILD_ID);
		
		if(m.containsKey(buildId)) return null;
		Config config = new Config(sender,infoMap,buildId);
		m.put(buildId,config);
		
		lastest = config;
		configAdded();
		
		return config;
	}
	
	
	public Object g() throws Exception
	{return m;}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("rootDir")) return rootDir;
		if(key.equals("latest")) return lastest;
		if(key.equals("configs")) return configs();
		
		if(key.equals("keys")) return new String[]{"rootDir","latest","configs"};
		
		throw new Exception("Unknown key: "+key);
	}
		
	
		
	private void println(String line) throws Exception
	{consoleGui.p(line);}
	
	
	private List configs()
	{return new ArrayList(m.values());}
	
	
	private void configAdded()
	{send(this,"configAdded()");}
	
	private void configRemoved()
	{send(this,"configRemoved()");}
	
	
	
	
	
	private class Config extends S1 implements V, R, P, F, T
	{
		private String buildId;
		private P sender;
		
		private String state;
		private Date dateStart;
		private Date dateEnd;
		
		private File dir;
		
		private File propFile;
		private File infoFile;
		private File logDir;
		
		private Map infoMap;
		private Map propMap;
		
		private String debugInfo;
		
		
		public Config(P sender, Map infoMap, String buildId) throws Exception
		{
			this.sender = sender;
			this.infoMap = infoMap;
			this.buildId = buildId;
			
			dir = new File(rootDir,buildId);
			logDir = new File(dir,"log");
			propFile = new File(dir,"prop.properties");
			infoFile = new File(dir,"info.properties");
			
			dir.mkdirs();
			logDir.mkdirs();
			
			propMap = (Map) readProp.t(propFile);
			if(propMap==null) propMap = new HashMap();
			
			writeProp.p(new Object[]{infoFile,infoMap});
			
			dateStart = new Date();
			state = STATE_CONNECTED;
			
			logger.p(new Object[]{logDir,infoMap,STATE_CONNECTED,dateStart});
		}
		
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals(EVENT)) {handleEvent((String) obj);return;}
			if(key.equals(AWT)) {handleAwt((String) obj);return;}
			if(key.equals(DEBUG)) {handleDebug((String) obj);return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		
		public void p(Object obj) throws Exception
		{sender.p(obj);}
		
		
		public Object t(Object obj) throws Exception
		{return null;}
		
		
		public boolean f(Object obj) throws Exception
		{return state.equals(STATE_CONNECTED);}
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("dir")) return dir;
			if(key.equals("logDir")) return logDir;
			if(key.equals("propFile")) return propFile;
			if(key.equals("infoFile")) return infoFile;
			
			if(key.equals("buildId")) return buildId;
			if(key.equals("infoMap")) return infoMap;
			if(key.equals("propMap")) return propMap;
			
			if(key.equals("state")) return state;
			if(key.equals("dateStart")) return dateStart;
			if(key.equals("dateEnd")) return dateEnd;
			
			if(key.equals("debugInfo")) return debugInfo;
			
			if(key.equals("keys")) return new String[]{
				"dir","logDir","propFile","infoFile",
				"buildId","infoMap","propMap",
				"state","dateStart","dateEnd",
				"debugInfo"};
				
			throw new Exception("Unknown key: "+key);
		}
		
		
		private void handleEvent(String event)
		{
			try
			{
				if(event.equals("lost")) connectionLost();
				else if(event.equals("closed")) connectionClosed();
				else throw new Exception("Unsupported event: "+event);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"handleEvent(String)",e);}
		}
		
		
		private void handleAwt(String awt)
		{
			try
			{
				println(buildId+": awt thread state: "+awt);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"handleAwt(String)",e);}
		}
		
		private void handleDebug(String debug)
		{
			try
			{
				debugInfo = debug;
				debugInfoReceived();
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"handleDebug(String)",e);}
		}
		
		
		private void connectionLost() throws Exception
		{
			println(buildId+": connection lost");
			over(STATE_LOST);
			if(isProp(PROP_WARNPOPUP_ONLOST,false)) warnPopupLost();
			if(isProp(PROP_RESTART_ONLOST,false)) restartAppli();
		}
		
		
		private void connectionClosed() throws Exception
		{
			println(buildId+": connection closed");
			over(STATE_CLOSED);
			if(isProp(PROP_RESTART_ONCLOSED,false)) restartAppli();
		}
		
		
		
		
		
		private void over(String newState) throws Exception
		{
			state = newState;
			dateEnd = new Date();
			
			writeProp.p(new Object[]{propFile,propMap});
			logger.p(new Object[]{logDir,infoMap,newState,dateEnd});
			
			m.remove(buildId);
			lastest = this;
			
			configRemoved();
			modified();
		}
		
		private void restartAppli() throws Exception
		{
			println(buildId+": restarting appli");
			executeStart.p(this);
		}
		
		private void warnPopupLost() throws Exception
		{
			warnPopup.p(buildId);
		}
		
		private boolean isProp(String key, boolean defaultValue)
		{
			if(!propMap.containsKey(key)) return defaultValue;
			String value = (String) propMap.get(key);
			return value.equals("true");
		}
		
		private void modified()
		{send(this,"modified()");}
		
		private void debugInfoReceived()
		{send(this,"debugInfoReceived()");}
	}
}
