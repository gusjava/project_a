package a.entity.gus06.sys.filetool.ext.dbviewer1.settings.holder.gui1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20230215";}
	
	public static final String DISPLAY_URL = "URL";
	public static final String DISPLAY_USER = "User";
	public static final String DISPLAY_PWD = "Pwd";

	private Service editor;
	
	private Map map;

	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.map.string.editor2");
		
		Map configMap = new HashMap();
		
		configMap.put("url.display",DISPLAY_URL);
		configMap.put("user.display",DISPLAY_USER);
		configMap.put("pwd.display",DISPLAY_PWD);
		configMap.put("struct","url;user;pwd");
		
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