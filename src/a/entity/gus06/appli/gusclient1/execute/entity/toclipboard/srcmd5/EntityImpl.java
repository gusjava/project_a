package a.entity.gus06.appli.gusclient1.execute.entity.toclipboard.srcmd5;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20150301";}
	

	private Service selection;
	private Service findDir;
	private Service dirToSrcmd5;
	private Service toClipboard;
	

	public EntityImpl() throws Exception
	{
		selection = Outside.service(this,"gus06.appli.gusclient1.gui.entity.holder");
		findDir = Outside.service(this,"gus06.entitydev.entityname.packagedir");
		dirToSrcmd5 = Outside.service(this,"gus06.entitydev.dirtosrcmd5.full");
		toClipboard = Outside.service(this,"gus.x.clipboard.string");
	}
	
	
	public void e() throws Exception
	{
		String name = (String) selection.g();
		if(name==null) return;
		
		File dir = (File) findDir.t(name);
		String md5 = (String) dirToSrcmd5.t(dir);
		
		toClipboard.p(md5);
	}
}
