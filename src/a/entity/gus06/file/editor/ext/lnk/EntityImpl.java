package a.entity.gus06.file.editor.ext.lnk;

import a.framework.*;
import java.io.File;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20170409";}

	
	private Service extractPath;
	private Service formPanel;
	private Service mainEditor;
	private Service buildDesc;
	
	private File file;
	private JPanel panel;
	


	public EntityImpl() throws Exception
	{
		extractPath = Outside.service(this,"gus06.file.lnk.extract.path");
		formPanel = Outside.service(this,"*gus06.swing.panel.formpanel.panel1");
		mainEditor = Outside.service(this,"*gus06.file.editor.main");
		buildDesc = Outside.service(this,"gus06.tostring.desc.short1.file");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) formPanel.i(),BorderLayout.NORTH);
		panel.add((JComponent) mainEditor.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null || !file.isFile() || file.length()==0) resetGui();
		else updateGui();
		
	}
	
	
	private void resetGui() throws Exception
	{
		formPanel.e();
		mainEditor.p(null);
	}
	
	private void updateGui() throws Exception
	{
		File target = (File) extractPath.t(file);
		String desc = (String) buildDesc.t(target);
		
		formPanel.e();
		formPanel.v("Target",desc);
		mainEditor.p(target);
	}
}