package a.entity.gus06.awt.screen.rect2;

import a.framework.*;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsConfiguration;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20220714";}

	
	
	public Object g() throws Exception
	{
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] devices = env.getScreenDevices();
		
		GraphicsDevice device = findOtherDevice();
		if(device==null) return null;
		
		GraphicsConfiguration gc = device.getDefaultConfiguration();
		return gc.getBounds();
	}
	
	private GraphicsDevice findOtherDevice() throws Exception
	{
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] devices = env.getScreenDevices();
		
		GraphicsDevice defaultDevice = env.getDefaultScreenDevice();
		String defaultId = defaultDevice.getIDstring();
		
		for(int j=0;j<devices.length;j++)
		{ 
			GraphicsDevice device = devices[j];
			if(!device.getIDstring().equals(defaultId)) return device;
		}
		return null;
	}
}