package a.entity.gus.x.awt.desktop.open;

import a.framework.*;
import java.awt.Desktop;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20240128";}


	private Service toFile;

	public EntityImpl() throws Exception
	{toFile = Outside.service(this,"gus.find.file");}	
	
	
	public void p(Object obj) throws Exception
	{
		if(!Desktop.isDesktopSupported())
			throw new Exception("Could not use command open: desktop not supported");
		if(!Desktop.getDesktop().isSupported(Desktop.Action.OPEN))
			throw new Exception("Could not use command open: desktop OPEN action not supported");

		File file = (File) toFile.t(obj);
		Desktop.getDesktop().open(file);
	}
}