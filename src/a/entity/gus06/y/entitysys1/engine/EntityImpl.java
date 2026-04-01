package a.entity.gus06.y.entitysys1.engine;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.Connection;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250925";}
	
	public static final String FILENAME_DB = "store";
	public static final String FILENAME_LOCK = "lock.txt";
	public static final String FILENAME_SEARCH = "search.txt";
	public static final String FILENAME_LASTTIME = "lasttime.txt";

	private Service dataLoaderEntity;
	private Service dataLoaderJar;
	private Service buildLocker;
	private Service accessFile;
	
	private Service buildCx;
	private Service findCompileErrMap;
	private Service findMissingLinkMap;
	private Service findSrcMap;
	
	private Service nameToDir;
	private Service nameToFile;
	private Service readFile;
	

	public EntityImpl() throws Exception
	{
		dataLoaderEntity = Outside.service(this,"gus06.y.entitysys1.dataloader.entity");
		dataLoaderJar = Outside.service(this,"gus06.y.entitysys1.dataloader.jar");
		buildLocker = Outside.service(this, "gus.y.entitysys1.buildlocker");
		accessFile = Outside.service(this,"gus06.file.access.string");
		
		buildCx = Outside.service(this,"gus06.y.entitydb1.buildcx");
		findCompileErrMap = Outside.service(this, "gus.y.entitydb1.entity_compile_err.findall");
		findMissingLinkMap = Outside.service(this, "gus.y.entitydb1.entity_missing_link.findall");
		findSrcMap = Outside.service(this, "gus.y.entitydb1.entity_src.findall");
		
		nameToDir = Outside.service(this,"gus06.x.entity.src.find.packagedir");
		nameToFile = Outside.service(this,"gus06.x.entity.src.find.entityfile");
		readFile = Outside.service(this, "gus.x.file.string.read.n");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Engine((Object[]) obj);}
	
	
	private class Engine extends S1 implements R, G, E, F, V
	{
		private File dataDir;
		private File srcDir;
		private File binDir;
		private File libDir;
		private File jdkDir;
		private String devId;
		private Set ignore1;
		
		private File dbFile;
		private File lockFile;
		private File searchFile;
		private File lastTimeFile;
		
		private G getCx;
		private Object locker;
		private Object accessSearch;
		private Object accessLastTime;
		
		private Map dataMap = new HashMap();
		private Map jarMap = new HashMap();
		private List nameList = new ArrayList();
		private Map compileErrMap = new HashMap();
		private Map missingLinkMap = new HashMap();
		private Map srcMap = new HashMap();
	
		private Object info;
		private List selectedNames;
		
		public Engine(Object[] o) throws Exception
		{
			if(o.length!=7) throw new Exception("Wrong data number: "+o.length);
			
			dataDir = (File) o[0];
			srcDir = (File) o[1];
			binDir = (File) o[2];
			libDir = (File) o[3];
			jdkDir = (File) o[4];
			devId = (String) o[5];
			ignore1 = (Set) o[6];
			
			dataDir.mkdirs();
			
			dbFile = new File(dataDir,FILENAME_DB);
			lockFile = new File(dataDir,FILENAME_LOCK);
			searchFile = new File(dataDir,FILENAME_SEARCH);
			lastTimeFile = new File(dataDir,FILENAME_LASTTIME);
			
			getCx = (G) buildCx.t(dbFile);
			locker = buildLocker.t(lockFile);
			accessSearch = accessFile.t(searchFile);
			accessLastTime = accessFile.t(lastTimeFile);
		}
		
		public boolean f(Object obj) throws Exception
		{return true;}
		
		
		public Object g() throws Exception
		{return dataMap;}
		
		
		public void e() throws Exception
		{load();}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("srcDir")) return srcDir;
			if(key.equals("binDir")) return binDir;
			if(key.equals("libDir")) return libDir;
			if(key.equals("jdkDir")) return jdkDir;
			if(key.equals("dbFile")) return dbFile;
			if(key.equals("devId")) return devId;
			if(key.equals("ignore1")) return ignore1;
			
			if(key.equals("compileErrMap")) return compileErrMap;
			if(key.equals("missingLinkMap")) return missingLinkMap;
			if(key.equals("srcMap")) return srcMap;
			if(key.equals("nameList")) return nameList;
			if(key.equals("dataMap")) return dataMap;
			if(key.equals("jarMap")) return jarMap;
			
			if(key.equals("cx")) return cx();
			if(key.equals("lastTime")) return lastTime();
			if(key.equals("search")) return search();
			if(key.equals("lockSet")) return lockSet();
			if(key.equals("info")) return info;
			if(key.equals("selectedNames")) return selectedNames;
			
			if(key.startsWith("dir_")) return dirFor(key.substring(4));
			if(key.startsWith("file_")) return fileFor(key.substring(5));
			if(key.startsWith("src_")) return srcFor(key.substring(4));
			
			if(key.equals("keys")) return new String[]{
				"srcDir", "binDir", "libDir", "jdkDir", "dbFile","devId", "ignore1",
				"compileErrMap", "missingLinkMap", "srcMap", "nameList",
				"cx", "lastTime", "search", "lockSet","info","selectedNames"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if (key.equals("search"))
			{persistSearch((String) obj);return;}
			
			if (key.equals("lock"))
			{lock(obj);return;}
			
			if (key.equals("unlock"))
			{unlock(obj);return;}
			
			if (key.equals("select"))
			{select(obj);return;}
			
			
			if (key.equals("entityAdded"))
			{handleEntityAdded(obj);return;}
			
			if (key.equals("entityRenamed"))
			{handleEntityRenamed(obj);return;}
			
			if (key.equals("entityDuplicated"))
			{handleEntityDuplicated(obj);return;}
			
			if (key.equals("entityDeleted"))
			{handleEntityDeleted(obj);return;}
			
			if (key.equals("entityModified"))
			{handleEntityModified(obj);return;}
			
			if (key.equals("srcSaved"))
			{handleSrcSaved(obj);return;}
			
			if (key.equals("srcCleared"))
			{handleSrcCleared(obj);return;}
			
			throw new Exception("Unknown key: "+key);
		}
	
		/*
		 * ACTIONS
		 */
		
		private void persistSearch(String search) throws Exception
		{((P) accessSearch).p(search);}
		
		
		private void persistLastTime(String lastTime) throws Exception
		{((P) accessLastTime).p(lastTime);}
		
		
		private void lock(Object obj) throws Exception
		{
			boolean done = ((F)locker).f(new Object[] {"lock", obj, nameList});
			if(done) locked();
		}
		
		private void unlock(Object obj) throws Exception
		{
			boolean done = ((F)locker).f(new Object[] {"unlock", obj, nameList});
			if(done) unlocked();
		}
	
		private void select(Object obj) throws Exception
		{
			if(obj instanceof List) {selectList((List) obj);return;}
			if(obj instanceof String) {selectList(List.of(obj));return;}
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
		
		private void selectList(List selection) throws Exception
		{
			selectedNames = new ArrayList();
			for(int i=0;i<selection.size();i++)
			{
				String name = (String) selection.get(i);
				if(nameList.contains(name)) selectedNames.add(name);
			}
			((P)locker).p(new Object[] {"lock", selectedNames, nameList});
			selected();
		}

		private void handleEntityAdded(Object info) throws Exception
		{
			this.info = info;
			load();
			lock(info);
			entityAdded();
		}
	
		private void handleEntityRenamed(Object info) throws Exception
		{
			this.info = info;
			load();
			lock(((String[])info)[1]);
			entityRenamed();
		}
	
		private void handleEntityDuplicated(Object info) throws Exception
		{
			this.info = info;
			load();
			lock(((String[])info)[1]);
			entityDuplicated();
		}
	
		private void handleEntityDeleted(Object info) throws Exception
		{
			this.info = info;
			load();
			entityDeleted();
		}
		
		private void handleEntityModified(Object info) throws Exception
		{
			this.info = info;
			load();
			entityModified();
		}
	
		private void handleSrcSaved(Object info) throws Exception
		{
			this.info = info;
			load();
			srcSaved();
		}
		
		private void handleSrcCleared(Object info) throws Exception
		{
			this.info = info;
			load();
			srcCleared();
		}
		
		
		private void load() throws Exception
		{
			dataMap = (Map) dataLoaderEntity.t(this);
			jarMap = (Map) dataLoaderJar.t(this);
			
			compileErrMap = (Map) findCompileErrMap.t(cx());
			missingLinkMap = (Map) findMissingLinkMap.t(cx());
			srcMap = (Map) findSrcMap.t(cx());
			
			nameList = new ArrayList(dataMap.keySet());
			Collections.sort(nameList);
			
			persistLastTime("" + System.currentTimeMillis());
			loaded();
		}
		
		private Connection cx() throws Exception
		{return (Connection) getCx.g();}
		
		
		private Long lastTime() throws Exception
		{
			String s = (String) ((G) accessLastTime).g();
			return s!=null && !s.equals("") ? Long.parseLong(s) : null;
		}
		
		
		private File dirFor(String entityName) throws Exception
		{return (File) nameToDir.t(new Object[]{srcDir, entityName});}
		
		
		private File fileFor(String entityName) throws Exception
		{return (File) nameToFile.t(new Object[]{srcDir, entityName});}
		
		
		private String srcFor(String entityName) throws Exception
		{return (String) readFile.t(fileFor(entityName));}
		
		
		private String search() throws Exception
		{return (String) ((G) accessSearch).g();}
		
		
		private Set lockSet() throws Exception
		{return (Set) ((G) locker).g();}
		
	
		/*
		 * EVENTS
		 */
		
		private void loaded()
		{send(this, "loaded()");}

		private void locked()
		{send(this, "locked()");}
	
		private void unlocked()
		{send(this, "unlocked()");}

		private void selected()
		{send(this, "selected()");}

		private void entityAdded()
		{send(this, "entityAdded()");}
	
		private void entityRenamed()
		{send(this, "entityRenamed()");}
	
		private void entityDuplicated()
		{send(this, "entityDuplicated()");}
	
		private void entityDeleted()
		{send(this, "entityDeleted()");}

		private void entityModified()
		{send(this, "entityModified()");}
	
		private void srcSaved()
		{send(this, "srcSaved()");}
		
		private void srcCleared()
		{send(this, "srcCleared()");}
	}
}
