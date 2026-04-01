package a.entity.gus06.appli.gusiconviewer.gui.button.opendir;

import a.framework.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class EntityImpl implements Entity, ActionListener, P, I {

	public String creationDate() {return "20160501";}


	private Service open;

	private JButton button;
	private File dir;
	

	public EntityImpl() throws Exception
	{
		open = Outside.service(this,"gus06.awt.desktop.open");
		
		button = new JButton("Open dir");
		button.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return button;}
	
	
	
	public void p(Object obj) throws Exception
	{dir = (File) obj;}



	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	
	private void perform()
	{
		try
		{
			if(dir==null || !dir.isDirectory()) return;
			open.p(dir);
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}

}
