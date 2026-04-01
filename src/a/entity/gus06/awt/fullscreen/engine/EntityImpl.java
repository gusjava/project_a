package a.entity.gus06.awt.fullscreen.engine;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JComponent;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, P, G, R {

	public String creationDate() {return "20151015";}


	private GraphicsEnvironment env;
	private GraphicsDevice device;
	
	private Engine engine;
	private JComponent comp;
	
	

	public EntityImpl() throws Exception
	{
		env = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
		device = env.getDefaultScreenDevice();
		
		if(!device.isFullScreenSupported())
		throw new Exception("full screen is not supported");
		
		engine = new Engine(device);
	}



	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{stopFullScreen();return;}
		
		if(obj instanceof JComponent)
		{
			if(comp==null)
			startFullScreen((JComponent)obj);
			else changeFullScreen((JComponent)obj);
			return;
		}
		throw new Exception("Invalid data: "+obj.getClass().getName());
	}

	
	
	public Object g() throws Exception
	{return comp;}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("frame")) return engine.frame();
		if(key.equals("keys")) return new String[]{"frame"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private void startFullScreen(JComponent comp)
	{
		this.comp = comp;
		engine.startFullScreen(comp);
		send(this,"startFullScreen(JComponent)");
	}
	
	public void changeFullScreen(JComponent comp)
	{
		this.comp = comp;
		engine.changeFullScreen(comp);
		send(this,"changeFullScreen(JComponent)");
	}
	
	public void stopFullScreen()
	{
		engine.stopFullScreen();
		send(this,"stopFullScreen()");
		comp = null;
	}
}
