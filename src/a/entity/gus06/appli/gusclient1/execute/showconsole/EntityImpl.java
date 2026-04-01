package a.entity.gus06.appli.gusclient1.execute.showconsole;

import a.framework.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, E, ActionListener {

	public String creationDate() {return "20140803";}

	public static final String DISPLAY = "GUI_console#Console";


	private Service projectManager;
	private Service consoleGui;
	private Service custFrame;
	private Service getPseudo;
	
	private JFrame frame;
	

	public EntityImpl() throws Exception
	{
		projectManager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		consoleGui = Outside.service(this,"*gus06.appli.gusclient1.gui.console");
		custFrame = Outside.service(this,"gus06.swing.frame.cust2.display");
		getPseudo = Outside.service(this,"gus06.entitydev.pseudo.find");
		
		frame = new JFrame();
		custFrame.v(display(),frame);
		
		JComponent comp = (JComponent) consoleGui.i();
		if(comp instanceof Scrollable)
			comp = new JScrollPane(comp);
		
		frame.setContentPane(comp);
		frame.setSize(600,400);
		frame.setLocationRelativeTo(null);
		
		projectManager.addActionListener(this);
	}
	
	
	
	public void e() throws Exception
	{
		frame.setVisible(true);
	}
	
	
	
	private String display() throws Exception
	{
		
		String projectId = (String) projectManager.g();
		String pseudo = (String) getPseudo.g();
		
		StringBuffer b = new StringBuffer();
		b.append(DISPLAY);
		if(pseudo!=null) b.append(" "+pseudo);
		if(projectId!=null) b.append(" - "+projectId);
		
		return b.toString();
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{projectChanged();}
	
	
	
	private void projectChanged()
	{
		try
		{
			custFrame.v(display(),frame);
		}
		catch(Exception e)
		{Outside.err(this,"projectChanged()",e);}
	}
}
