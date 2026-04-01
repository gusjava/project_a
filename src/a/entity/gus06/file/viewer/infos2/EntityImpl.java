package a.entity.gus06.file.viewer.infos2;

import a.framework.*;
import javax.swing.JPanel;
import java.io.File;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20160528";}
	
	public static final Dimension DIM1 = new Dimension(500,400);
	public static final Dimension DIM2 = new Dimension(300,400);


	private Service formPanel;
	private Service previewPanel;

	private JPanel panel;
	private File file;

	public EntityImpl() throws Exception
	{
		formPanel = Outside.service(this,"*gus06.file.viewer.infos2.form");
		previewPanel = Outside.service(this,"*gus06.file.viewer.infos2.preview");
		
		JComponent formComp = (JComponent) formPanel.i();
		JComponent previewComp = (JComponent) previewPanel.i();
		
		formComp.setPreferredSize(DIM1);
		previewComp.setPreferredSize(DIM2);
		
		panel = new JPanel(new BorderLayout());
		panel.setOpaque(true);
		panel.setBackground(Color.WHITE);
		
		panel.add(formComp, BorderLayout.CENTER);
		panel.add(previewComp, BorderLayout.EAST);
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		
		formPanel.p(file);
		previewPanel.p(file);
	}
}