package a.entity.gus06.appli.gusexplorer.execute.tools.script.console;

import a.framework.*;
import javax.swing.JFrame;
import javax.swing.JComponent;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20201225";}
	
	public static final String TITLE = "CONSOLE_gus#Gus Script console";
	

	private Service consoleGui1;
	private Service custFrame;
	
	private JFrame frame;


	public EntityImpl() throws Exception
	{
		consoleGui1 = Outside.service(this,"*gus06.appli.laboscript.gui.consolegui");
		custFrame = Outside.service(this,"gus06.swing.frame.cust2.display");
		
		JComponent comp = (JComponent) consoleGui1.i();
		
		frame = new JFrame();
		frame.setContentPane(comp);
		custFrame.v(TITLE,frame);
		frame.setSize(1200,600);
		frame.setLocationRelativeTo(null);
	}
	
	
	public void e() throws Exception
	{
		frame.setVisible(true);
	}
}