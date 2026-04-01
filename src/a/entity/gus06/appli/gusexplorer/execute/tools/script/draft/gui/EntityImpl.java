package a.entity.gus06.appli.gusexplorer.execute.tools.script.draft.gui;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20191021";}
	
	public static final String FILENAME = "draft.gus";


	private Service editor;
	
	private File dir;
	private File scriptFile;
	
	private JPanel panel;
	private JButton reset_button;
	

	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.file.editor.ext.gus");
		
		dir = (File) Outside.resource(this,"defaultdir");
		scriptFile = new File(dir,FILENAME);
		
		reset_button = new JButton("Reset");
		reset_button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) editor.i(),BorderLayout.CENTER);
		panel.add(reset_button,BorderLayout.SOUTH);
		
		if(!scriptFile.exists()) reset();
		else load();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	private void reset()
	{
		try
		{
			PrintStream p = new PrintStream(scriptFile);
			p.println("@code\n\n\n");
			p.close();
			
			editor.p(scriptFile);
		}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
	}
	
	
	private void load()
	{
		try
		{
			editor.p(scriptFile);
		}
		catch(Exception e)
		{Outside.err(this,"load()",e);}
	}


	public void actionPerformed(ActionEvent e)
	{reset();}
}
