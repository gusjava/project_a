package a.entity.gus06.file.editor.ext.pdf;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20150617";}


	private Service screen;
	private Service readImage;

	private File file;
	
	private JPanel panel;

	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus06.swing.panel.screen.image");
		readImage = Outside.service(this,"gus06.file.read.image.from.pdf");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) screen.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(fileExists()) screen.p(readImage.t(file));
		else screen.p(null);
	}
	
	
	private boolean fileExists()
	{return file!=null && file.isFile();}
}