package a.entity.gus06.dir.hdd.vol.serialnumber;

import java.io.File;
import javax.swing.filechooser.FileSystemView;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191126";}


	private Service checkOs;
	private Service handleWindows;

	public EntityImpl() throws Exception
	{
		checkOs = Outside.service(this,"gus06.env.oscheck");
		handleWindows = Outside.service(this,"gus06.env.windows.hdd.vol.serialnumber");
	}

	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		if(f==null) return "";
		
		if(checkOs.f("windows")) return handleWindows.t(f);
		return "###";
	}
}
