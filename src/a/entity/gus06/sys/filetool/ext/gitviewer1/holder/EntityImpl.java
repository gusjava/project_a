package a.entity.gus06.sys.filetool.ext.gitviewer1.holder;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20200120";}
	
	
	private Service findRoot;
	private Service mainGui;
	
	private Map map;
	private File root;
	
	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		mainGui = Outside.service(this,"*gus06.sys.git1.gui.maingui");
	}
	
	
	public Object i() throws Exception
	{return mainGui.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		root = (File) findRoot.t(map);
		
		mainGui.p(root);
	}
	
	
	
	private String get(String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) throw new Exception("Key not found inside tool: "+key);
		return (String) map.get(key);
	}
}