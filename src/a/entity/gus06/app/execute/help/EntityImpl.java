package a.entity.gus06.app.execute.help;

import a.framework.*;
import javax.swing.JFrame;
import javax.swing.JComponent;

public class EntityImpl extends S1 implements Entity, E, P {

	public String creationDate() {return "20160919";}

	public static final String DISPLAY = "ACTION_help#Help";

	private Service custFrame;
	private Service helpPanel;
	
	private JFrame frame;
	


	public EntityImpl() throws Exception
	{
		custFrame = Outside.service(this,"gus06.swing.frame.cust2.display");
		helpPanel = Outside.service(this,"*gus06.app.execute.help.panel");
		
		JComponent comp = (JComponent) helpPanel.i();
		
		frame = new JFrame();
		frame.setSize(1200,800);
		frame.setLocationRelativeTo(null);
		frame.setContentPane(comp);
		
		custFrame.v(DISPLAY,frame);
	}
	
	
	public void e() throws Exception
	{
		frame.setVisible(true);
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		frame.setVisible(true);
		helpPanel.p(obj);
	}
}