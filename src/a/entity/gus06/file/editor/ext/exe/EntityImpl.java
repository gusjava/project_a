package a.entity.gus06.file.editor.ext.exe;

import java.awt.BorderLayout;
import java.io.File;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20200429";}

	
	private Service launch;
	
	private JPanel panel;
	private JButton button;
	
	private File file;
	

	public EntityImpl() throws Exception
	{
		launch = Outside.service(this,"gus06.awt.desktop.open");
		
		button = new JButton("Launch");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {launch();}
		});
				
		panel = new JPanel(new BorderLayout());
		panel.add(button,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null || !file.isFile() || file.length()==0) resetGui();
		else updateGui();
	}
	
	
	private void resetGui() throws Exception
	{button.setEnabled(false);}
	
	
	private void updateGui() throws Exception
	{button.setEnabled(true);}
	
	
	
	private void launch()
	{
		try
		{
			launch.p(file);
		}
		catch(Exception e)
		{Outside.err(this,"launch()",e);}
	}
}