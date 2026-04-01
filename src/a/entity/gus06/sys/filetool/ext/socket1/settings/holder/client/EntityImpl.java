package a.entity.gus06.sys.filetool.ext.socket1.settings.holder.client;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250402";}
	
	
	public static final String KEY_REMOTE_PORT = "remote.port";
	public static final String KEY_REMOTE_IP = "remote.ip";
	
	public static final String[] STRUCT = new String[]{
		KEY_REMOTE_PORT, 
		KEY_REMOTE_IP};
	
	public static final String DISPLAY_REMOTE_PORT = "Port de connexion";
	public static final String DISPLAY_REMOTE_IP = "IP de connexion";

	private Service editor;
	
	private Map map;

	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.map.string.editor2");
		
		Map configMap = new HashMap();
		
		configMap.put(KEY_REMOTE_PORT+".display",	DISPLAY_REMOTE_PORT);
		configMap.put(KEY_REMOTE_IP+".display",		DISPLAY_REMOTE_IP);
		
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