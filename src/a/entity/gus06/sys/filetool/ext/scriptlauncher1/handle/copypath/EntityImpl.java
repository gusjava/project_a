package a.entity.gus06.sys.filetool.ext.scriptlauncher1.handle.copypath;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220608";}

	private Service clipboard;
	private Service findFile;

	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus.x.clipboard.string");
		findFile = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.find.scriptfile");
	}
	
	public void p(Object obj) throws Exception
	{
		File file = (File) findFile.t(obj);
		if(!file.exists()) throw new Exception("Script file not found: "+file);
		clipboard.p(file.getAbsolutePath());
	}
}