package a.entity.gus06.file.editor.ext.dll;

import a.framework.*;

import java.io.File;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20250210";}

	
	private Service findType;
	
	private JPanel panel;
	private JLabel label;
	
	private File file;
	private String type;
	

	public EntityImpl() throws Exception
	{
		findType = Outside.service(this,"gus06.env.windows.dll.findtype");
		
		label = new JLabel(" ");
				
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
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
	
	
	private void updateGui() throws Exception
	{
		type = (String) findType.t(file);
		label.setText("type: "+type);
	}
	
	private void resetGui() throws Exception
	{
		type = null;
		label.setText(" ");
	}
}