package a.entity.gus06.appli.gusclient1.gui.entity.editor;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.io.File;


public class EntityImpl implements Entity, I, P, ActionListener {

	public String creationDate() {return "20140724";}


	private Service javaEditor;
	private Service entityLabel;
	private Service toolPanel;
	private Service findFile;
	private Service isOwn;


	private JPanel panel;

	private String name;
	private File file;
	


	public EntityImpl() throws Exception
	{
		javaEditor = Outside.service(this,"*gus06.file.editor.ext.java");
		entityLabel = Outside.service(this,"*gus06.app.entity.label");
		toolPanel = Outside.service(this,"*gus06.appli.gusclient1.tool.shiftpanel");
		findFile = Outside.service(this,"gus06.entitydev.retrieve.javafile1");
		isOwn = Outside.service(this,"gus06.app.entity.name.own");

		panel = new JPanel(new BorderLayout());
		
		panel.add((JComponent) entityLabel.i(),BorderLayout.NORTH);
		panel.add((JComponent) javaEditor.i(),BorderLayout.CENTER);
		panel.add((JComponent) toolPanel.i(),BorderLayout.SOUTH);
		
		toolPanel.addActionListener(this);
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		name = (String) obj;
	
		if(name==null) resetGui();
		else updateGui();
	}
	
	
	
	
			
	
	private void updateGui() throws Exception
	{
		file = (File) findFile.t(name);
	
		entityLabel.p(name);
		updateEditor();
		updateTool();
	}
	
	
	
	private void resetGui() throws Exception
	{
		file = null;
		
		entityLabel.p(null);
		javaEditor.p(null);
		updateTool();
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{updateTool();}
	
	
	
	
	private void updateEditor()
	{
		try
		{
			boolean editable = isOwn.f(name);
			editorComp().setEditable(editable);
			javaEditor.p(file);
		}
		catch(Exception e)
		{Outside.err(this,"updateEditor()",e);}
	}
	
	
	
	private void updateTool()
	{
		try
		{
			toolPanel.p(new Object[]{name,file,editorComp()});
		}
		catch(Exception e)
		{Outside.err(this,"updateTool()",e);}
	}
	
	
	
	
	
	
	private JTextComponent editorComp() throws Exception
	{return (JTextComponent) javaEditor.r("comp");}
	
}
