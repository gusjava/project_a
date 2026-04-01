package a.entity.gus06.appli.gusiconviewer.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;


public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20160421";}


	private Service viewer;
	private Service dirLabel;
	private Service dirPersist;
	private Service dirButton;


	private JPanel panel;

	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.appli.gusiconviewer.gui.panel");
		dirLabel = Outside.service(this,"*gus06.appli.gusiconviewer.gui.dirlabel");
		dirPersist = Outside.service(this,"gus06.app.persister1.data.file");
		dirButton = Outside.service(this,"gus06.appli.gusiconviewer.gui.button.opendir");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) dirLabel.i(),BorderLayout.NORTH);
		panel.add((JComponent) viewer.i(),BorderLayout.CENTER);
		panel.add((JComponent) dirButton.i(),BorderLayout.SOUTH);
		
		dirLabel.addActionListener(this);
		dirLabel.p(getDir());
	}
	
	
	public Object i() throws Exception
	{return panel;}


	public void actionPerformed(ActionEvent e)
	{updatePanel();}
	
	
	private void updatePanel()
	{
		try
		{
			File dir = (File) dirLabel.g();
			
			viewer.p(dir);
			dirButton.p(dir);
			setDir(dir);
		}
		catch(Exception e)
		{Outside.err(this,"updatePanel()",e);}
	}
	


	private File getDir() throws Exception
	{return (File) dirPersist.r(getClass().getName()+"_dir");}
	
	private void setDir(File dir) throws Exception
	{dirPersist.v(getClass().getName()+"_dir",dir);}
}
