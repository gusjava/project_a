package a.entity.gus06.swing.frame.perform1;

import a.framework.*;
import javax.swing.JFrame;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20180118";}
	
	
	public void v(String key, Object obj) throws Exception
	{
		JFrame frame = (JFrame) obj;
		
		if(key.equals("switch")) switch1(frame);
		else if(key.equals("show")) show(frame);
		else if(key.equals("hide")) hide(frame);
		
		else throw new Exception("Invalid command: "+key);
	}
	
	
	private void show(JFrame frame)
	{
		frame.setVisible(true);
		frame.toFront();
	}
	
	private void hide(JFrame frame)
	{
		frame.setVisible(false);
	}
	
	private void switch1(JFrame frame)
	{
		if(frame.isVisible()) hide(frame);
		else show(frame);
	}
}
