package a.entity.gus06.file.editor.ext.bat;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.io.File;

public class EntityImpl implements Entity, ActionListener, I, P, G, R, V {

	public String creationDate() {return "20220209";}
	

	
	private Service txtEditor;
	private Service launch;
	
	private JPanel panel;
	private JButton button;
	
	private File file;
	

	public EntityImpl() throws Exception
	{
		txtEditor = Outside.service(this,"*gus06.file.editor.ext.txt");
		launch = Outside.service(this,"gus06.awt.desktop.open");
		
		button = new JButton("Execute batch");
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) txtEditor.i(), BorderLayout.CENTER);
		panel.add(button, BorderLayout.SOUTH);
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return txtEditor.g();}
	
	
	public Object r(String key) throws Exception
	{return txtEditor.r(key);}
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null || !file.isFile()) resetGui();
		else updateGui();
		
		txtEditor.p(file);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{txtEditor.v(key, obj);}
	
	
	private void resetGui() throws Exception
	{button.setEnabled(false);}
	
	
	private void updateGui() throws Exception
	{button.setEnabled(true);}



	public void actionPerformed(ActionEvent e)
	{launch();}
	
	
	private void launch()
	{
		try{launch.p(file);}
		catch(Exception e)
		{Outside.err(this,"launch()",e);}
	}
}