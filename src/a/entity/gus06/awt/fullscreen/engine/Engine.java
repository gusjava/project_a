package a.entity.gus06.awt.fullscreen.engine;

import java.awt.BorderLayout;
import java.awt.DisplayMode;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Engine {

	
	private GraphicsDevice device;
	private GraphicsConfiguration gc;
	
	private DisplayMode initialDM;
	private DisplayMode[] DMs;
	private DisplayMode bestDM;
	
	private JFrame frame;
	private JPanel pane;
	
	public JFrame frame() {return frame;}
	
	
	
	public Engine(GraphicsDevice device)
	{
		this.device = device;
		gc = device.getDefaultConfiguration();
		
		initialDM = device.getDisplayMode();
		DMs = device.getDisplayModes();
		bestDM = DMs[DMs.length-1];
		
		pane = new JPanel(new BorderLayout());
		
		frame = new JFrame(gc);
		frame.setUndecorated(true); 
		frame.setContentPane(pane);
	}
	
	
	
	public void startFullScreen(JComponent component)
	{
		pane.add(component);

		if(device.isDisplayChangeSupported())
			device.setDisplayMode(bestDM);

		frame.setContentPane(pane);
		frame.setExtendedState(JFrame.NORMAL);
		frame.setVisible(true);
		device.setFullScreenWindow(frame);
		frame.validate();
	}
	
	
	public void changeFullScreen(JComponent component)
	{
		pane.removeAll();
		pane.add(component);
		frame.validate();
	}
	
	
	public void stopFullScreen()
	{
		pane.removeAll();
		device.setDisplayMode(initialDM);
		device.setFullScreenWindow(null);
		frame.setVisible(false);
	}
}
