package a.entity.gus06.sys.filetool.ext.repartition1.holder;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.awt.Color;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20160915";}
	

	private Service findRoot;
	private Service colorArray;
	private Service mainGui;
	
	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		colorArray = Outside.service(this,"gus06.sys.filetool.ext.repartition1.colorarray");
		mainGui = Outside.service(this,"*gus06.sys.filetool.ext.repartition1.gui.maingui");
	}
	
	
	
	public Object i() throws Exception
	{return mainGui.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		File root = (File) findRoot.t(map);
		Color[] colors = (Color[]) colorArray.t(map);
		
		mainGui.v("colors",colors);
		mainGui.p(root);
	}
}
