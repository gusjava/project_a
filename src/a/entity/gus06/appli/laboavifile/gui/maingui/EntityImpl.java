package a.entity.gus06.appli.laboavifile.gui.maingui;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;


public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20150616";}


	private Service chooseFile;
	private Service dsjGui;


	private JPanel panel;
	private JButton button;
	

	public EntityImpl() throws Exception
	{
		chooseFile = Outside.service(this,"gus06.file.choose.open.file.ext.avi.en");
		dsjGui = Outside.service(this,"*gus06.file.video.dsj.viewer");
		
		button = new JButton("Open AVI file");
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(button,BorderLayout.NORTH);
		panel.add((JComponent) dsjGui.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}


	public void actionPerformed(ActionEvent e)
	{openFile();}
	
	
	private void openFile()
	{
		try
		{
			File file = (File) chooseFile.g();
			if(file==null || !file.isFile()) return;
			
			dsjGui.p(file);
		}
		catch(Exception e)
		{Outside.err(this,"openFile()",e);}
	}
}
