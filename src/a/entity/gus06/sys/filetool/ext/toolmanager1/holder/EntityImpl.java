package a.entity.gus06.sys.filetool.ext.toolmanager1.holder;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250211";}
	
	private Service gui;
	private Service findRoot;
	
	private Map map;
	private File root;
	

	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		gui = Outside.service(this,"*gus06.sys.toolmanager1.gui.maingui");
	}
	
	public Object i() throws Exception
	{return gui.i();}
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		root = (File) findRoot.t(map);
		gui.p(root);
	}
}