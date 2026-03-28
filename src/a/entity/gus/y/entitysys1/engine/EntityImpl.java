package a.entity.gus.y.entitysys1.engine;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a.framework.E;
import a.framework.Entity;
import a.framework.F;
import a.framework.G;
import a.framework.Outside;
import a.framework.R;
import a.framework.S1;
import a.framework.Service;

public class EntityImpl extends S1 implements Entity, G, R, E, F {
	public String creationDate() {return "20240111";}

	public static final String PERSIST_KEY = EntityImpl.class.getName() + "_last";

	private Service findCompileErrMap;
	private Service findXyzErrMap;
	private Service findMissingLinkMap;
	private Service findSrcSaveMap;
	
	private Service dataLoader;
	private Service getCx;
	private Service getRootDir;
	private Service persist;
	private Service getDevId;

	private Long lastTime;
	private Map dataMap = new HashMap();
	private List nameList = new ArrayList();
	
	private Map compileErrMap = new HashMap();
	private Map xyzErrMap = new HashMap();
	private Map missingLinkMap = new HashMap();
	private Map srcSaveMap = new HashMap();

	public EntityImpl() throws Exception {
		findCompileErrMap = Outside.service(this, "gus.y.entitydb1.entity_compile_err.findall");
		findXyzErrMap = Outside.service(this, "gus.y.entitydb1.entity_xyz_err.findall");
		findMissingLinkMap = Outside.service(this, "gus.y.entitydb1.entity_missing_link.findall");
		findSrcSaveMap = Outside.service(this, "gus.y.entitydb1.entity_src_save.findall");
		
		dataLoader = Outside.service(this, "gus.y.entitysys1.dataloader");
		getCx = Outside.service(this, "gus.y.entitydb1.cx.main");
		getRootDir = Outside.service(this, "gus.y.srcroot1");
		getDevId = Outside.service(this, "gus.y.srcroot1.dev");
		persist = Outside.service(this, "gus.y.persist1.main");
		
		load();
	}

	public void e() throws Exception {
		load();
	}

	public Object g() throws Exception {
		return dataMap;
	}

	public Object r(String key) throws Exception {
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

		if (key.equals("keys")) return new String[] { 
			"rootDir", "binDir", "libDir", "cx", "devId",  "nameList", "lastTime",
			"xyzErrMap", "compileErrMap",  "missingLinkMap", "srcSaveMap" };

		throw new Exception("Unknown key: " + key);
	}

	public boolean f(Object obj) throws Exception {
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
		if (permission.equals("canModifyEntity"))
			return canModifyEntity((String) o[1]);

		throw new Exception("Unknown permission: " + permission);
	}

	/*
	 * LOAD
	 */

	private void load() throws Exception {
		if (performLoad())
			loaded();
	}

	private boolean performLoad() throws Exception {
		if (getRootDir() == null)
			return false;

		lastTime = findLastTime();
		dataMap = (Map) dataLoader.t(this);
		compileErrMap = (Map) findCompileErrMap.t(getCx());
		xyzErrMap = (Map) findXyzErrMap.t(getCx());
		missingLinkMap = (Map) findMissingLinkMap.t(getCx());
		srcSaveMap = (Map) findSrcSaveMap.t(getCx());
		
		persist.v(PERSIST_KEY, "" + System.currentTimeMillis());

		nameList = new ArrayList(dataMap.keySet());
		Collections.sort(nameList);
		return true;
	}

	/*
	 * LAST TIME
	 */

	private long findLastTime() throws Exception {
		String str = (String) persist.r(PERSIST_KEY);
		return str != null ? Long.parseLong(str) : 0L;
	}

	/*
	 * DEV
	 */

	private String getDevId() throws Exception {
		return (String) getDevId.g();
	}

	/*
	 * CX
	 */

	private Connection getCx() throws Exception {
		return (Connection) getCx.g();
	}

	/*
	 * DIRECTORIES
	 */

	private File getRootDir() throws Exception {
		return (File) getRootDir.g();
	}

	private File getBinDir() throws Exception {
		File rootDir = getRootDir();
		return rootDir!=null ? new File(rootDir.getParentFile(), "bin") : null;
	}

	private File getLibDir() throws Exception {
		File rootDir = getRootDir();
		return rootDir!=null ? new File(rootDir.getParentFile(), "lib") : null;
	}

	/*
	 * CAN
	 */

	private boolean canCreateEntity() throws Exception {
		return getDevId()!=null;
	}

	private boolean canDeleteEntity(String entityName) throws Exception {
		return isMyEntity(entityName);
	}

	private boolean canRenameEntity(String entityName) throws Exception {
		return isMyEntity(entityName);
	}

	private boolean canDuplicateEntity(String entityName) throws Exception {
		return getDevId()!=null && entityName != null;
	}

	private boolean canModifyEntity(String entityName) throws Exception {
		return isMyEntity(entityName);
	}

	/*
	 * IS MY ENTITY
	 */

	private boolean isMyEntity(String entityName) throws Exception {
		return entityName != null && entityName.startsWith(getDevId() + ".");
	}

	/*
	 * EVENTS
	 */

	private void loaded() {
		send(this, "loaded()");
	}
}
