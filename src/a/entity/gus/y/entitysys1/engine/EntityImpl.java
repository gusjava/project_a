package a.entity.gus.y.entitysys1.engine;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, R, E, F, V {
	public String creationDate() {return "20240111";}

	public static final String PERSIST_KEY = EntityImpl.class.getName() + "_last";

	private Service findCompileErrMap;
	private Service findXyzErrMap;
	private Service findMissingLinkMap;
	private Service findSrcSaveMap;
	private Service hasRights;
	
	private Service dataLoaderEntity;
	private Service dataLoaderJar;
	private Service getCx;
	private Service getRootDir;
	private Service persist;
	private Service getDevId;

	private Long lastTime;
	private Map dataMap = new HashMap();
	private Map jarMap = new HashMap();
	private List nameList = new ArrayList();
	private Object info;
	
	private Map compileErrMap = new HashMap();
	private Map xyzErrMap = new HashMap();
	private Map missingLinkMap = new HashMap();
	private Map srcSaveMap = new HashMap();

	public EntityImpl() throws Exception
	{
		findCompileErrMap = Outside.service(this, "gus.y.entitydb1.entity_compile_err.infosbyname");
		findXyzErrMap = Outside.service(this, "gus.y.entitydb1.entity_xyz_err.findall");
		findMissingLinkMap = Outside.service(this, "gus.y.entitydb1.entity_missing_link.findall");
		findSrcSaveMap = Outside.service(this, "gus.y.entitydb1.entity_src_save.findall");
		hasRights = Outside.service(this, "gus.x.entity.hasrights");
		
		dataLoaderEntity = Outside.service(this, "gus.y.entitysys1.dataloader.entity");
		dataLoaderJar = Outside.service(this, "gus.y.entitysys1.dataloader.jar");
		getCx = Outside.service(this, "gus.y.entitydb1.cx.main");
		getRootDir = Outside.service(this, "gus.y.srcroot1");
		getDevId = Outside.service(this, "gus.y.srcroot1.dev");
		persist = Outside.service(this, "gus.y.persist1.main");
		
		load();
	}

	public void e() throws Exception
	{
		new Thread(() -> {load();}).start();
	}

	public Object g() throws Exception
	{
		return dataMap;
	}

	public Object r(String key) throws Exception
	{
		if (key.equals("rootDir")) return getRootDir();
		if (key.equals("binDir")) return getBinDir();
		if (key.equals("libDir")) return getLibDir();
		if (key.equals("cx")) return getCx();
		if (key.equals("devId")) return getDevId();
		if (key.equals("nameList")) return nameList;
		if (key.equals("lastTime")) return lastTime;
		if (key.equals("xyzErrMap")) return xyzErrMap;
		if (key.equals("compileErrMap")) return compileErrMap;
		if (key.equals("missingLinkMap")) return missingLinkMap;
		if (key.equals("srcSaveMap")) return srcSaveMap;
		if (key.equals("jarMap")) return jarMap;
		if (key.equals("info")) return info;

		if (key.equals("keys")) return new String[] {
			"rootDir", "binDir", "libDir", "cx", 
			"devId", "nameList", "lastTime",
			"xyzErrMap", "compileErrMap", 
			"missingLinkMap", "srcSaveMap", 
			"jarMap", "info" };

		throw new Exception("Unknown key: " + key);
	}

	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2)
			throw new Exception("Wrong data number: " + o.length);

		String permission = (String) o[0];
		
		if (permission.equals("canCreateEntity"))
			return canCreateEntity();
			
		if (permission.equals("canDeleteEntity"))
			return canDeleteEntity((String) o[1]);
			
		if (permission.equals("canRenameEntity"))
			return canRenameEntity((String) o[1]);
			
		if (permission.equals("canDuplicateEntity"))
			return canDuplicateEntity((String) o[1]);
			
		if (permission.equals("canReplaceEntity"))
			return canReplaceEntity((String) o[1]);
			
		if (permission.equals("canModifyEntity"))
			return canModifyEntity((String) o[1]);

		throw new Exception("Unknown permission: " + permission);
	}

	/*
	 * LOAD
	 */

	private void load()
	{
		try
		{
			if (getRootDir() == null) throw new Exception("Root dir not found");
			loading();
			
			lastTime = findLastTime();
			dataMap = (Map) dataLoaderEntity.t(this);
			jarMap = (Map) dataLoaderJar.t(this);
			compileErrMap = (Map) findCompileErrMap.t(getCx());
			xyzErrMap = (Map) findXyzErrMap.t(getCx());
			missingLinkMap = (Map) findMissingLinkMap.t(getCx());
			srcSaveMap = (Map) findSrcSaveMap.t(getCx());
			
			persist.v(PERSIST_KEY, "" + System.currentTimeMillis());
	
			nameList = new ArrayList(dataMap.keySet());
			Collections.sort(nameList);
			
			loaded();
		}
		catch(Exception e)
		{Outside.err(this,"load()",e);}
	}

	/*
	 * LAST TIME
	 */

	private long findLastTime() throws Exception
	{
		String str = (String) persist.r(PERSIST_KEY);
		return str != null ? Long.parseLong(str) : 0L;
	}

	/*
	 * DEV
	 */

	private String getDevId() throws Exception
	{
		return (String) getDevId.g();
	}

	/*
	 * CX
	 */

	private Connection getCx() throws Exception
	{
		return (Connection) getCx.g();
	}

	/*
	 * DIRECTORIES
	 */

	private File getRootDir() throws Exception {
		return (File) getRootDir.g();
	}

	private File getBinDir() throws Exception
	{
		File rootDir = getRootDir();
		return rootDir!=null ? new File(rootDir.getParentFile(), "bin") : null;
	}

	private File getLibDir() throws Exception
	{
		File rootDir = getRootDir();
		return rootDir!=null ? new File(rootDir.getParentFile(), "lib") : null;
	}

	/*
	 * CAN
	 */

	private boolean canCreateEntity() throws Exception
	{return getDevId()!=null;}

	private boolean canDuplicateEntity(String entityName) throws Exception
	{return entityName != null && getDevId()!=null;}

	private boolean canDeleteEntity(String entityName) throws Exception
	{return entityName != null && hasRights(entityName);}

	private boolean canRenameEntity(String entityName) throws Exception
	{return entityName != null && hasRights(entityName);}
	
	private boolean canReplaceEntity(String entityName) throws Exception
	{return entityName != null && hasRights(entityName);}

	private boolean canModifyEntity(String entityName) throws Exception
	{return entityName != null && hasRights(entityName);}

	/*
	 * HAS RIGHTS
	 */

	private boolean hasRights(String entityName) throws Exception
	{return hasRights.f(new Object[]{getDevId(), entityName});}

	/*
	 * V
	 */

	public void v(String key, Object obj) throws Exception
	{
		if (key.equals("entityAdded"))     { handleEntityAdded(obj);      return; }
		if (key.equals("entitiesAdded"))   { handleEntitiesAdded(obj);    return; }
		if (key.equals("entityRenamed"))   { handleEntityRenamed(obj);    return; }
		if (key.equals("entityDuplicated")){ handleEntityDuplicated(obj); return; }
		if (key.equals("entityReplaced"))   { handleEntityReplaced(obj);   return; }
		if (key.equals("entitiesReplaced")) { handleEntitiesReplaced(obj); return; }
		if (key.equals("entityDeleted"))    { handleEntityDeleted(obj);    return; }
		if (key.equals("entitiesDeleted"))  { handleEntitiesDeleted(obj);  return; }
		if (key.equals("entityModified"))   { handleEntityModified(obj);   return; }
		if (key.equals("srcSaved"))        { handleSrcSaved(obj);        return; }
		if (key.equals("srcCleared"))      { handleSrcCleared(obj);      return; }
		throw new Exception("Unknown key: " + key);
	}

	private void handleEntityAdded(Object info) throws Exception
	{
		this.info = info;
		new Thread(() -> {load(); entityAdded();}).start();
	}

	private void handleEntitiesAdded(Object info) throws Exception
	{
		this.info = info;
		new Thread(() -> {load(); entitiesAdded(); }).start();
	}

	private void handleEntityRenamed(Object info) throws Exception
	{
		this.info = info;
		new Thread(() -> {load(); entityRenamed(); }).start();
	}

	private void handleEntityDuplicated(Object info) throws Exception
	{
		this.info = info;
		new Thread(() -> {load(); entityDuplicated(); }).start();
	}
	
	private void handleEntityReplaced(Object info) throws Exception
	{
		this.info = info;
		new Thread(() -> {load(); entityReplaced(); }).start();
	}

	private void handleEntitiesReplaced(Object info) throws Exception
	{
		this.info = info;
		new Thread(() -> {load(); entitiesReplaced(); }).start();
	}

	private void handleEntityDeleted(Object info) throws Exception
	{
		this.info = info;
		new Thread(() -> {load(); entityDeleted();}).start();
	}

	private void handleEntitiesDeleted(Object info) throws Exception
	{
		this.info = info;
		new Thread(() -> {load(); entitiesDeleted();}).start();
	}

	private void handleEntityModified(Object info) throws Exception
	{
		this.info = info;
		new Thread(() -> {load(); entityModified();}).start();
	}

	private void handleSrcSaved(Object info) throws Exception
	{
		this.info = info;
		new Thread(() -> {load(); srcSaved();}).start();
	}

	private void handleSrcCleared(Object info) throws Exception
	{
		this.info = info;
		new Thread(() -> {load(); srcCleared(); }).start();
	}

	/*
	 * EVENTS
	 */

	private void loading()
	{send(this, "loading()");}
	
	private void loaded()
	{send(this, "loaded()");}

	private void entityAdded()
	{send(this, "entityAdded()");}

	private void entitiesAdded()
	{send(this, "entitiesAdded()");}

	private void entityRenamed()
	{send(this, "entityRenamed()");}

	private void entityDuplicated()
	{send(this, "entityDuplicated()");}
	
	private void entityReplaced()
	{send(this, "entityReplaced()");}

	private void entitiesReplaced()
	{send(this, "entitiesReplaced()");}

	private void entityDeleted()
	{send(this, "entityDeleted()");}

	private void entitiesDeleted()
	{send(this, "entitiesDeleted()");}

	private void entityModified()
	{send(this, "entityModified()");}

	private void srcSaved()
	{send(this, "srcSaved()");}

	private void srcCleared()
	{send(this, "srcCleared()");}
}
