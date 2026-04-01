package a.entity.gus06.swing.comp.handleframe.states;

import a.framework.*;
import java.awt.Component;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;


public class EntityImpl implements Entity, V {

	public String creationDate() {return "20160505";}


	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("show")) {show((Component) obj);return;}
		if(key.equals("hide")) {hide((Component) obj);return;}
		
		if(key.equals("decorate")) {decorate((Component) obj);return;}
		if(key.equals("undecorate")) {undecorate((Component) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void show(Component comp)
	{
		Component frame = SwingUtilities.getRoot(comp);
		frame.setVisible(true);
	}
	
	private void hide(Component comp)
	{
		Component frame = SwingUtilities.getRoot(comp);
		frame.setVisible(false);
	}

	private void decorate(Component comp)
	{
		Component frame = SwingUtilities.getRoot(comp);
		((JFrame) frame).setUndecorated(false);
	}
	
	private void undecorate(Component comp)
	{
		Component frame = SwingUtilities.getRoot(comp);
		((JFrame) frame).setUndecorated(true);
	}
}
