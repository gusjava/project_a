package a.entity.gus06.appli.gusexplorer.execute.tools.script.draft;

import a.framework.*;
import javax.swing.JFrame;
import javax.swing.JComponent;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20191020";}
	
	public static final String TITLE = "FILE_gus#Gus Script Draft";
	

	private Service draftGui1;
	private Service custFrame;
	
	private JFrame frame;


	public EntityImpl() throws Exception
	{
		draftGui1 = Outside.service(this,"*gus06.appli.gusexplorer.execute.tools.script.draft.gui");
		custFrame = Outside.service(this,"gus06.swing.frame.cust2.display");
		
		JComponent comp = (JComponent) draftGui1.i();
		
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
