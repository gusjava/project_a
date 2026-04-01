package a.entity.gus06.swing.frame.undecorated.shift;

import a.framework.*;
import javax.swing.JFrame;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180216";}
	
	
	public Object t(Object obj) throws Exception
	{
		JFrame frame = (JFrame) obj;
		JFrame frame1 = new JFrame();
			
		frame1.setUndecorated(!frame.isUndecorated());
		
		frame1.setTitle(frame.getTitle());
		frame1.setIconImage(frame.getIconImage());
		frame1.setBounds(frame.getBounds());
		frame1.setAlwaysOnTop(frame.isAlwaysOnTop());
		frame1.setVisible(frame.isVisible());
		
		frame1.setContentPane(frame.getContentPane());
		frame.dispose();
		
		return frame1;
	}
}
