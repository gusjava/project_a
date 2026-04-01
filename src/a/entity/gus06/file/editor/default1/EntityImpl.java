package a.entity.gus06.file.editor.default1;

import java.awt.BorderLayout;
import java.io.File;
import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Insets;
import javax.swing.JScrollPane;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20231026";}


	private Service getMaxSize;

	
	private JPanel panel;
	private JTextArea area;
	
	private File file;
	
	public EntityImpl() throws Exception
	{
		getMaxSize = Outside.service(this,"gus06.file.editor.main.checksize");
		
		area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		area.setEditable(false);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
	}
	
	public Object i() throws Exception
	{return panel;}
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		area.setText(buildDescription());
	}
	
	private String buildDescription() throws Exception
	{
		if(file==null || !file.isFile()) return "";
		
		StringBuffer b = new StringBuffer();
		b.append("current file size: "+file.length()+"\n");
		b.append("limit size for autoloading: "+getMaxSize.g()+"\n\n");
		b.append("Autoloading has been disabled for this file.\n");
		b.append("If you want to load it, click the button below.\n");
		return b.toString();
	}
}
