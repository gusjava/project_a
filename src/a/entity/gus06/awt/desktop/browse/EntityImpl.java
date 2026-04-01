package a.entity.gus06.awt.desktop.browse;

import a.framework.*;
import java.net.URI;
import java.awt.Desktop;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140917";}


	private Service toURI;

	public EntityImpl() throws Exception
	{toURI = Outside.service(this,"gus06.find.uri");}
	
	
	public void p(Object obj) throws Exception
	{
		if(!Desktop.isDesktopSupported())
			throw new Exception("Could not use command browse: desktop not supported");
		if(!Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
			throw new Exception("Could not use command browse: desktop BROWSE action not supported");

		URI uri = (URI) toURI.t(obj);
		Desktop.getDesktop().browse(uri);
	}
}
