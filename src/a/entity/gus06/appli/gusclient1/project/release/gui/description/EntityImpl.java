package a.entity.gus06.appli.gusclient1.project.release.gui.description;

import a.framework.*;
import javax.swing.*;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.Properties;
import java.awt.Insets;

public class EntityImpl implements Entity, ActionListener, I, P {

	public String creationDate() {return "20141022";}

	public static final String FILENAME = "info.properties";
	

	private Service textDelayed;
	private Service readFile;
	private Service writeFile;
	
	private JPanel panel;
	private JTextArea area;
	
	private File dir;
	private File file;
	
	
	public EntityImpl() throws Exception
	{
		textDelayed = Outside.service(this,"gus06.swing.textcomp.textchanged.delayed");
		readFile = Outside.service(this,"gus.x.file.prop.read");
		writeFile = Outside.service(this,"gus06.file.write.properties");
		
		area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		
		S sup = (S) textDelayed.t(area);
		sup.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		File dir = (File) obj;
		file = new File(dir,FILENAME);
		readData();
	}



	public void actionPerformed(ActionEvent e)
	{writeData();}
	
	
	
	
	private void readData()
	{
		try
		{
			if(file==null || !file.exists()) return;
			
			Properties prop = (Properties) readFile.t(file);
			String text = prop.getProperty("description");
			area.setText(text);
		}
		catch(Exception e)
		{Outside.err(this,"readData()",e);}
	}
	
	
	
	private void writeData()
	{
		try
		{
			if(file==null || !file.exists()) return;
			
			Properties prop = (Properties) readFile.t(file);
			String text = area.getText();
			prop.setProperty("description",text);
			writeFile.p(new Object[]{file,prop});
		}
		catch(Exception e)
		{Outside.err(this,"writeData()",e);}
	}
	
	
	
	
}
