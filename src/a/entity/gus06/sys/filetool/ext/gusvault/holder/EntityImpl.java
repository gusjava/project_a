package a.entity.gus06.sys.filetool.ext.gusvault.holder;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20200416";}
	
	public static final String KEY_PERSIST = "persist";
	

	private Service findRoot;
	private Service mainGui;

	private Map map;
	private File root;


	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		mainGui = Outside.service(this,"*gus06.sys.vault1.gui.maingui");
	}
	
	
	public Object i() throws Exception
	{return mainGui.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		root = (File) findRoot.t(map);
		
		String persist = get(KEY_PERSIST);
		
		mainGui.v("persistKey",persist);
		mainGui.p(root);
	}
	
	
	private String get(String key)
	{return map.containsKey(key) ? (String) map.get(key) : null;}
}