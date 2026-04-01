package a.entity.gus06.sys.filetool.ext.openrouter.settings.holder;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20251203";}
	
	public static final String DISPLAY_APIKEY = "API key";
	public static final String DISPLAY_STOREDIR = "Store dir";
	public static final String DISPLAY_TIMEOUT = "Timeout (ms)";

	private Service editor;
	
	private Map map;

	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.map.string.editor2");
		
		Map configMap = new HashMap();
		
		configMap.put("apikey.display",DISPLAY_APIKEY);
		configMap.put("storedir.display",DISPLAY_STOREDIR);
		configMap.put("timeout.display",DISPLAY_TIMEOUT);
		
		configMap.put("struct","apikey;storedir;timeout");
		
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