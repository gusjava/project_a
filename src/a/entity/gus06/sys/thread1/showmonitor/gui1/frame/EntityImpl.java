package a.entity.gus06.sys.thread1.showmonitor.gui1.frame;

import a.framework.*;
import javax.swing.JFrame;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180124";}


	private Service guiFactory;

	public EntityImpl() throws Exception
	{
		guiFactory = Outside.service(this,"factory#gus.sys.thread1.showmonitor.gui1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Thread t = (Thread) obj;
    		
		Object gui = guiFactory.g();
		JComponent comp = (JComponent) ((I) gui).i();
		((P) gui).p(t);
    	
		JFrame frame = new JFrame("Monitor for ["+t.getName()+"]");
		
		frame.setContentPane(comp);
		frame.setSize(800,500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
