package a.entity.gus06.sys.runtask1.gui1.frame;

import a.framework.*;
import javax.swing.JFrame;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150609";}
	
	public static final String DISPLAY = "TASK#Tasks";


	private Service gui;
	private Service custFrame;
	private Service persistFrame;
	private Service performFrame;
	
	private JFrame frame;


	public EntityImpl() throws Exception
	{
		gui = Outside.service(this,"*gus06.sys.runtask1.gui1");
		custFrame = Outside.service(this,"gus06.swing.frame.cust2.display");
		persistFrame = Outside.service(this,"gus06.swing.frame.persister.bounds");
		performFrame = Outside.service(this,"gus06.swing.frame.perform1");
		
		frame = new JFrame();
		frame.setContentPane((JComponent) gui.i());
		frame.setSize(600,200);
		frame.setLocationRelativeTo(null);
		
		custFrame.v(DISPLAY,frame);
		persistFrame.v(getClass().getName()+"_frame",frame);
	}
	
	
	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		performFrame.v(s,frame);
	}
}
