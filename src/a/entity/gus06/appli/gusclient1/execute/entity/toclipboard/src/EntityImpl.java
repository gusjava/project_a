package a.entity.gus06.appli.gusclient1.execute.entity.toclipboard.src;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20150225";}
	

	private Service selection;
	private Service findDir;
	private Service dirToSrc;
	private Service toClipboard;

	public EntityImpl() throws Exception
	{
		selection = Outside.service(this,"gus06.appli.gusclient1.gui.entity.holder");
		findDir = Outside.service(this,"gus06.entitydev.entityname.packagedir");
		dirToSrc = Outside.service(this,"gus06.entitydev.dirtosrc.full");
		toClipboard = Outside.service(this,"gus.x.clipboard.string");
	}
	
	
	public void e() throws Exception
	{
		String name = (String) selection.g();
		if(name==null) return;
		
		File dir = (File) findDir.t(name);
		String src = (String) dirToSrc.t(dir);
		
		toClipboard.p(src);
	}
}
