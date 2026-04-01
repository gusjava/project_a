package a.entity.gus06.awt.desktop.mail;

import a.framework.*;
import java.net.URI;
import java.awt.Desktop;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140917";}

	
	
	public void p(Object obj) throws Exception
	{
		if(!Desktop.isDesktopSupported())
			throw new Exception("Could not use command browse: desktop not supported");
		if(!Desktop.getDesktop().isSupported(Desktop.Action.MAIL))
			throw new Exception("Could not use command mail: desktop MAIL action not supported");

		URI uri = new URI("mailto:"+obj);
		Desktop.getDesktop().browse(uri);
	}
}
