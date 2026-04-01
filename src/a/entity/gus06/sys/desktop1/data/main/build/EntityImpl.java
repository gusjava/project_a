package a.entity.gus06.sys.desktop1.data.main.build;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191121";}
	
	public static final String KEY_GUI_PANEL = "gui_panel";
	public static final String KEY_GUI_PANE = "gui_pane";
	public static final String KEY_GUI_BAR = "gui_bar";

	public static final String KEY_DIR_ROOT = "dir_root";
	public static final String KEY_DIR_STORE = "dir_store";
	public static final String KEY_DIR_SCRIPT = "dir_script";
	public static final String KEY_DIR_CONFIG = "dir_config";
	public static final String KEY_DIR_IMAGE = "dir_image";
	public static final String KEY_DIR_RESOURCE = "dir_resource";
	public static final String KEY_DIR_ITEM_DEF = "dir_item_def";
	public static final String KEY_DIR_ITEM_POS = "dir_item_pos";
	
	public static final String KEY_FILE_PROP = "file_prop";
	public static final String KEY_PROP = "prop";
	public static final String KEY_ITEM_MANAGER = "item_manager";
	public static final String KEY_OBJECT_FACTORY = "object_factory";
	public static final String KEY_MAP_ACCESS = "map_access";
	public static final String KEY_OBJ_ACCESS = "obj_access";
	public static final String KEY_RES_ACCESS = "res_access";
	
	
	
	private Service readProp;
	private Service buildItemManager;
	private Service buildObjFactory;
	private Service buildMapAccess;
	private Service buildObjAccess;
	private Service resAccessBuilder;
	private Service uniqueEntity;

	public EntityImpl() throws Exception
	{
		readProp = Outside.service(this,"gus06.file.read.properties");
		buildItemManager = Outside.service(this,"gus06.sys.desktop1.item.manager");
		buildObjFactory = Outside.service(this,"gus06.sys.objfactory1.engine.builder2");
		buildMapAccess = Outside.service(this,"gus06.sys.store2.build.mapaccess.dir.properties");
		buildObjAccess = Outside.service(this,"gus06.sys.store2.object.builder1");
		resAccessBuilder = Outside.service(this,"gus06.dirfile.relpath.access.builder");
		uniqueEntity = Outside.service(this,"entityunique");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		File rootDir = (File) o[0];
		Object panel = o[1];
		Object pane = o[2];
		Object bar = o[3];
		
		
		File storeDir = new File(rootDir,"store");
		File scriptDir = new File(rootDir,"script");
		File configDir = new File(rootDir,"config");
		File imageDir = new File(rootDir,"image");
		File resourceDir = new File(rootDir,"resource");
		File itemDefDir = new File(rootDir,"item_def");
		File itemPosDir = new File(rootDir,"item_pos");
		
		storeDir.mkdirs();
		scriptDir.mkdirs();
		configDir.mkdirs();
		imageDir.mkdirs();
		resourceDir.mkdirs();
		itemDefDir.mkdirs();
		itemPosDir.mkdirs();
		
		File propFile = new File(configDir,"prop.properties");
		if(!propFile.isFile()) throw new Exception("File not found: "+propFile);
		
		Map prop = (Map) readProp.t(propFile);
		
		Object mapAccess = buildMapAccess.t(storeDir);
		Object objAccess = buildObjAccess.t(new Object[]{mapAccess,null});
		Object resAccess = resAccessBuilder.t(resourceDir);
		
		Map ruleMap = new HashMap();
		ruleMap.put("map",mapAccess);
		ruleMap.put("obj",objAccess);
		ruleMap.put("res",resAccess);
		
		Iterator it = prop.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(key.startsWith("factory."))
			{
				String factoryName = key.substring(8);
				String factoryValue = (String) prop.get(key);
				T factory = (T) uniqueEntity.t(factoryValue);
				ruleMap.put(factoryName,factory);
			}
		}
		
		R objFactory = (R) buildObjFactory.t(ruleMap);
		((V) objAccess).v("factory",objFactory);
		
		
		Map main = new HashMap();
		Object itemManager = buildItemManager.t(main);
		
		main.put(KEY_GUI_PANEL,panel);
		main.put(KEY_GUI_PANE,pane);
		main.put(KEY_GUI_BAR,bar);
		main.put(KEY_DIR_ROOT,rootDir);
		main.put(KEY_DIR_STORE,storeDir);
		main.put(KEY_DIR_SCRIPT,scriptDir);
		main.put(KEY_DIR_CONFIG,configDir);
		main.put(KEY_DIR_IMAGE,imageDir);
		main.put(KEY_DIR_RESOURCE,resourceDir);
		main.put(KEY_DIR_ITEM_DEF,itemDefDir);
		main.put(KEY_DIR_ITEM_POS,itemPosDir);
		main.put(KEY_FILE_PROP,propFile);
		main.put(KEY_PROP,prop);
		main.put(KEY_MAP_ACCESS,mapAccess);
		main.put(KEY_OBJ_ACCESS,objAccess);
		main.put(KEY_RES_ACCESS,resAccess);
		main.put(KEY_OBJECT_FACTORY,objFactory);
		main.put(KEY_ITEM_MANAGER,itemManager);
		
		return main;
	}
}
