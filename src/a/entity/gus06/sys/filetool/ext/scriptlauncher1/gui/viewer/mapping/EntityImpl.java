package a.entity.gus06.sys.filetool.ext.scriptlauncher1.gui.viewer.mapping;

import a.framework.*;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I, P, V {

	public String creationDate() {return "20220605";}


	private Service propEditor;

	private File mappingFile;
	private File scriptFile;
	
	
	private JPanel panel;

	public EntityImpl() throws Exception
	{
		propEditor = Outside.service(this,"*gus06.file.editor.ext.properties");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) propEditor.i(), BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		scriptFile = (File) obj;
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("mappingFile"))
		{
			mappingFile = (File) obj;
			propEditor.p(mappingFile);
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
}
