package a.entity.gus06.file.editor.ext.image;

import a.framework.*;
import javax.swing.JComponent;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140909";}


	private Service editionPanel;
	private Service editionBar;
	private Service labelDesc;
	private Service readImage;
	private Service writeImage;

	private File file;
	private Object image;
	
	private JPanel panel;
	private JToolBar bar;
	

	public EntityImpl() throws Exception
	{
		editionPanel = Outside.service(this,"*gus06.data.editor.renderedimage.editor1.panel");
		editionBar = Outside.service(this,"*gus06.data.editor.renderedimage.editor1.bar");
		labelDesc = Outside.service(this,"*gus06.data.editor.renderedimage.editor1.labeldesc");
		readImage = Outside.service(this,"gus06.file.read.image.generic");
		writeImage = Outside.service(this,"gus06.file.write.image.all");
		
		bar = (JToolBar) editionBar.i();
		bar.setOrientation(JToolBar.VERTICAL);
		
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add((JComponent) editionPanel.i(),BorderLayout.CENTER);
		panel1.add((JComponent) labelDesc.i(),BorderLayout.SOUTH);
		
		
		panel = new JPanel(new BorderLayout());
		panel.add(panel1,BorderLayout.CENTER);
		panel.add(bar,BorderLayout.WEST);
		
		editionBar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{imageEditedByBar();}
		});
		editionPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{imageEditedByPanel();}
		});
	}
	
	
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		image = loadImage();
		
		editionPanel.p(image);
		editionBar.p(image);
		labelDesc.p(image);
	}
	
	
	
	private void imageEditedByBar()
	{
		try
		{
			image = editionBar.g();
			editionPanel.p(image);
			labelDesc.p(image);
			writeImage();
		}
		catch(Exception e)
		{Outside.err(this,"imageEditedByBar()",e);}
	}
	
	private void imageEditedByPanel()
	{
		try
		{
			image = editionPanel.g();
			editionBar.p(image);
			labelDesc.p(image);
			writeImage();
		}
		catch(Exception e)
		{Outside.err(this,"imageEditedByPanel()",e);}
	}
	
	
	
	private Object loadImage() throws Exception
	{
		if(file==null) return null;
		if(!file.isFile()) return null;
		return readImage.t(file);
	}
	
	private void writeImage() throws Exception
	{
		if(file==null) return;
		writeImage.p(new Object[]{file,image});
	}
}