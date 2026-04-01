package a.entity.gus06.awt.window.tofront;

import a.framework.*;
import java.awt.Window;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190305";}
	
	
	public void p(Object obj) throws Exception
	{
		Window w = (Window) obj;
		w.toFront();
	}
}
