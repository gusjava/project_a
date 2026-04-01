package a.entity.gus06.appli.gusclient1.execute.entity.opendir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20141014";}


	private Service selection;
	private Service findDir;
	private Service openDir;
	

	public EntityImpl() throws Exception
	{
		selection = Outside.service(this,"gus06.appli.gusclient1.gui.entity.holder");
		findDir = Outside.service(this,"gus06.entitydev.entityname.packagedir");
		openDir = Outside.service(this,"gus06.awt.desktop.open");
	}
	
	
	public void e() throws Exception
	{
		String name = (String) selection.g();
		if(name==null) return;
		
		File dir = (File) findDir.t(name);
		openDir.p(dir);
	}
}
