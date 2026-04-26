package a.entity.gus06.file.editor.main2;

import a.framework.*;
import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Action;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, I, P, R, G, E {

	public String creationDate() {return "20200331";}

	

	private Service fileLabel;
	private Service fileToName;
	private Service shiftPanel;
	private Service barPanel;
	private Service tipAction;
	private Service buildBar;
	private Service editorBuilder;
	private Service recorder;
	private Service executeF5;
	
	private JPanel panel;
	private String editorName;
	private Object editor;
	private File file;
	
	private ActionListener fileLabelListener;
	

	public EntityImpl() throws Exception
	{
		fileLabel = Outside.service(this,"*gus06.swing.label.hold.file.v");
		fileToName = Outside.service(this,"gus06.file.editor.main.filetoname");
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		barPanel = Outside.service(this,"*gus06.file.editor.main.barpanel");
		tipAction = Outside.service(this,"*gus06.app.execute.help.tipaction");
		buildBar = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		editorBuilder = Outside.service(this,"*gus06.file.editor.main.builder.async");
		recorder = Outside.service(this,"gus06.file.editor.main.recorder");
		executeF5 = Outside.service(this,"gus06.swing.comp.cust3.execute.f5");
		
		Action action = (Action) tipAction.g();
		JComponent tipBar = (JComponent) buildBar.t(action);
		
		JComponent fileComp = (JComponent) fileLabel.i();
		
		JPanel panelTop = new JPanel(new BorderLayout());
		panelTop.add(fileComp, BorderLayout.CENTER);
		panelTop.add(tipBar, BorderLayout.EAST);
		
		panelTop.setBorder(fileComp.getBorder());
		fileComp.setBorder(BorderFactory.createEmptyBorder());
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) fileLabel.i(),BorderLayout.NORTH);
		panel.add((JComponent) shiftPanel.i(),BorderLayout.CENTER);
		panel.add((JComponent) barPanel.i(),BorderLayout.SOUTH);
		
		executeF5.p(new Object[]{fileLabel.i(),this});
		
		fileLabelListener = new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{labelToEditor();}
		};
		
		fileLabel.addActionListener(fileLabelListener);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	
	public void e() throws Exception
	{p(file);}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		refreshEditor();
		editorToLabel();
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("file")) return file;
		if(key.equals("editor")) return editor;
		if(key.equals("editorName")) return editorName;
		if(key.equals("fileLabel")) return fileLabel;
		
		if(key.equals("keys")) return new String[]{"file","editor","editorName","fileLabel"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void refreshEditor() throws Exception
	{
		if(editor!=null) ((P)editor).p(null);
		
		editorName = (String) fileToName.t(file);
		editor = editorBuilder.t(file);
		tipAction.p(editorName);
		
		shiftPanel.p(editor);
		barPanel.p(editor);
		
		recorder.p(new Object[]{this,file});
	}
	
	
	private void editorToLabel() throws Exception
	{
		fileLabel.removeActionListener(fileLabelListener);
		fileLabel.p(file);
		fileLabel.addActionListener(fileLabelListener);
	}
	
	
	private void labelToEditor()
	{
		try
		{
			file = (File) fileLabel.g();
			refreshEditor();
			received();
		}
		catch(Exception e)
		{Outside.err(this,"labelToEditor()",e);}
	}
	
	
	private void received()
	{send(this,"received()");}
}