package a.entity.gus06.sys.filetool.ext.socket1.settings.holder.server;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250402";}
	
	
	public static final String KEY_ROOT = "path.root";
	public static final String KEY_LOCAL_PORT = "local.port";
	public static final String KEY_LOCAL_FILE_PORT = "local.file.port";
	
	public static final String[] STRUCT = new String[]{
		KEY_ROOT, 
		KEY_LOCAL_PORT, 
		KEY_LOCAL_FILE_PORT};
	
	public static final String DISPLAY_ROOT = "R�pertoire de stockage";
	public static final String DISPLAY_LOCAL_PORT = "Port du serveur";
	public static final String DISPLAY_LOCAL_FILE_PORT = "Port pour l'envoi de fichiers";

	private Service editor;
	
	private Map map;

	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.map.string.editor2");
		
		Map configMap = new HashMap();
		
		configMap.put(KEY_ROOT+".display",		DISPLAY_ROOT);
		configMap.put(KEY_LOCAL_PORT+".display",		DISPLAY_LOCAL_PORT);
		configMap.put(KEY_LOCAL_FILE_PORT+".display",	DISPLAY_LOCAL_FILE_PORT);
		
		configMap.put("struct",STRUCT);
		
		editor.v("init",configMap);
	}
	
	
	public Object i() throws Exception
	{return editor.i();}
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		editor.p(map);
	}
}