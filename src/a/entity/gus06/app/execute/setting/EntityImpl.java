package a.entity.gus06.app.execute.setting;

import a.framework.*;
import javax.swing.JFrame;
import javax.swing.JComponent;

public class EntityImpl extends S1 implements Entity, E {

	public String creationDate() {return "20170512";}

	public static final String DISPLAY = "ACTION_setting#Settings";

	private Service custFrame;
	private Service settingPanel;
	
	private JFrame frame;
	


	public EntityImpl() throws Exception
	{
		custFrame = Outside.service(this,"gus06.swing.frame.cust2.display");
		settingPanel = Outside.service(this,"*gus06.app.execute.setting.panel");
		
		JComponent comp = (JComponent) settingPanel.i();
		
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
}
