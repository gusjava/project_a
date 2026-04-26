package a.entity.gus.x.awt.screen.rect;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20240104";}
	
	public Object g() throws Exception
	{
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		GraphicsConfiguration gc = device.getDefaultConfiguration();
		return gc.getBounds();
	}
}
