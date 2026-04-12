package a.entity.gus06.sys.filetool.ext.compta1.holder;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20260106";}

	private Service findRoot;	
	private Service gui;
	
	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		gui = Outside.service(this,"*gus06.sys.compta1.gui.main");
	}
	
	public Object i() throws Exception
	{return gui.i();}
	
	public void p(Object obj) throws Exception
	{
		File root = (File) findRoot.t(obj);
		gui.p(root);
	}
}
