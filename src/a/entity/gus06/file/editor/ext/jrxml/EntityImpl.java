package a.entity.gus06.file.editor.ext.jrxml;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.io.File;

public class EntityImpl implements Entity, ActionListener, I, P, G, R, V {

	public String creationDate() {return "20221019";}
	
	
	private Service editor;
	private Service generate;
	private Service open;
	
	private JPanel panel;
	private JButton button;
	
	private File file;
	

	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.file.editor.ext.xhtml");
		generate = Outside.service(this,"gus06.file.pdf.jasper.generate1.ask");
		open = Outside.service(this,"gus06.awt.desktop.open");
		
		button = new JButton("Generate PDF");
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) editor.i(), BorderLayout.CENTER);
		panel.add(button, BorderLayout.SOUTH);
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return editor.g();}
	
	
	public Object r(String key) throws Exception
	{return editor.r(key);}
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null || !file.isFile()) resetGui();
		else updateGui();
		
		editor.p(file);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{editor.v(key, obj);}
	
	
	private void resetGui() throws Exception
	{button.setEnabled(false);}
	
	
	private void updateGui() throws Exception
	{button.setEnabled(true);}



	public void actionPerformed(ActionEvent e)
	{generate();}
	
	
	private void generate()
	{
		try{
			File pdf = (File) generate.t(file);
			if(pdf!=null && pdf.exists()) open.p(pdf);
		}
		catch(Exception e)
		{Outside.err(this,"generate()",e);}
	}
}
