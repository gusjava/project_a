package a.entity.gus06.dirfile.op.select;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201123";}


	private Service checkOs;
	private Service handleWindows;
	private Service open;

	public EntityImpl() throws Exception
	{
		checkOs = Outside.service(this,"gus06.env.oscheck");
		handleWindows = Outside.service(this,"gus06.env.windows.command.selectfile");
		open = Outside.service(this,"gus06.awt.desktop.open");
	}

	public void p(Object obj) throws Exception
	{
		File f = (File) obj;
		if(checkOs.f("windows")) handleWindows.p(f);
		else open.p(f.getParentFile());
	}
}