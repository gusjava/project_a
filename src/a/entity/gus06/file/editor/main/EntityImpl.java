package a.entity.gus06.file.editor.main;

import a.framework.*;
import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, P, R, V, G, E {

	public String creationDate() {return "20140723";}

	public static final String DEFAULT_MAXSIZE = "gus.file.editor.default1";
	

	private Service fileLabel;
	private Service fileToName;
	private Service shiftPanel;
	private Service barPanel;
	private Service tipAction;
	private Service buildBar;
	private Service editorBuilder;
	private Service recorder;
	private Service executeF5;
	private Service checkSize;
	
	private JPanel panel;
	private String editorName;
	private Object editor;
	private File file;
	

	public EntityImpl() throws Exception
	{
		fileLabel = Outside.service(this,"*gus06.swing.label.hold.file");
		fileToName = Outside.service(this,"gus06.file.editor.main.filetoname");
		shiftPanel = Outside.service(this,"*gus06.swing.panel.shiftpanel");
		barPanel = Outside.service(this,"*gus06.file.editor.main.barpanel");
		tipAction = Outside.service(this,"*gus06.app.execute.help.tipaction");
		buildBar = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		editorBuilder = Outside.service(this,"*gus06.file.editor.main.builder.async");
		recorder = Outside.service(this,"gus06.file.editor.main.recorder");
		executeF5 = Outside.service(this,"gus06.swing.comp.cust3.execute.f5");
		checkSize = Outside.service(this,"gus06.file.editor.main.checksize");
		
		Action action = (Action) tipAction.g();
		JComponent tipBar = (JComponent) buildBar.t(action);
		
		JComponent fileComp = (JComponent) fileLabel.i();
		
		JPanel panelTop = new JPanel(new BorderLayout());
		panelTop.add(fileComp, BorderLayout.CENTER);
		panelTop.add(tipBar, BorderLayout.EAST);
		
		panelTop.setBorder(fileComp.getBorder());
		fileComp.setBorder(BorderFactory.createEmptyBorder());
		
		panel = new JPanel(new BorderLayout());
		panel.add(panelTop,BorderLayout.NORTH);
		panel.add((JComponent) shiftPanel.i(),BorderLayout.CENTER);
		panel.add((JComponent) barPanel.i(),BorderLayout.SOUTH);
		
		executeF5.p(new Object[]{fileLabel.i(),this});
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
		refresh(true);
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
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(editor==null) throw new Exception("Failed to call V on editor: null");
		if(!(editor instanceof V)) throw new Exception("V not available for editor: "+editor);
		
		((V)editor).v(key,obj);
	}
	
	
	
	private void refresh(boolean check) throws Exception
	{
		if(editor!=null) ((P)editor).p(null);
		
		if(check && !checkSize.f(file))
		{
			editorName = DEFAULT_MAXSIZE;
			editor = editorBuilder.t(new Object[]{file, editorName});
			shiftPanel.p(checkSizePanel());
		}
		else
		{
			editorName = (String) fileToName.t(file);
			editor = editorBuilder.t(new Object[]{file, editorName});
			shiftPanel.p(editor);
		}
		
		fileLabel.p(file);
		barPanel.p(editor);
		tipAction.p(editorName);
		recorder.p(new Object[]{this,file});
	}
	
	
	private JPanel checkSizePanel() throws Exception
	{
		JButton button = new JButton("Load");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{refresh();}
		});
		JPanel p = new JPanel(new BorderLayout());
		JComponent comp = (JComponent) ((I) editor).i();
		p.add(comp, BorderLayout.CENTER);
		p.add(button, BorderLayout.SOUTH);
		return p;
	}
	
	private void refresh()
	{
		try{refresh(false);}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
}