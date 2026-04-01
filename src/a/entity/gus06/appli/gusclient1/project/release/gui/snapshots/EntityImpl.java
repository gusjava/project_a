package a.entity.gus06.appli.gusclient1.project.release.gui.snapshots;

import a.framework.*;
import javax.swing.*;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, ActionListener, I, P {

	public String creationDate() {return "20141015";}


	private Service manager;
	private Service findFrame;
	private Service printScreen;
	private Service writeFile;
	private Service screenPanel;
	
	private JPanel panel;
	private JButton button;
	
	private File dir;
	
	
	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		findFrame = Outside.service(this,"gus06.appli.gusclient1.project.release.gui.snapshots.findframe");
		printScreen = Outside.service(this,"gus06.awt.robot.printscreen");
		writeFile = Outside.service(this,"gus06.file.write.image.jpg");
		screenPanel = Outside.service(this,"*gus06.swing.panel.screen.image");
		
		button = new JButton("Take picture");
		button.setEnabled(false);
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(button,BorderLayout.NORTH);
		panel.add((JComponent) screenPanel.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		dir = (File) obj;
		button.setEnabled(dir!=null);
		screenPanel.p(null);
	}


	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	
	
	private void perform()
	{
		try
		{
			if(dir==null) return;
			
			String id = (String) manager.g();
			if(id==null) throw new Exception("Invalid null id found for project");
			
			Rectangle rec = (Rectangle) findFrame.t(id);
			if(rec==null) throw new Exception("Invalid null rec found for application");
			
			BufferedImage image = (BufferedImage) printScreen.t(rec);
			if(image==null) throw new Exception("Invalid null image found for application");
			
			File imageDir = new File(dir,"images");
			imageDir.mkdirs();
			
			File file = new File(imageDir,"image_"+now()+".jpg");
			writeFile.p(new Object[]{file,image});
			
			screenPanel.p(image);
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
	
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	private String now() {return sdf.format(new Date());}

}
