package a.entity.gus06.file.editor.ext.db.h2trace;

import java.awt.BorderLayout;
import java.io.File;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250723";}


	private Service viewer;
	private Service readFile;
	
	private JPanel panel;
	private JButton button;
	
	private File file;
	

	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.data.viewer.string");
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
				
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) viewer.i() ,BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null || !file.isFile() || file.length()==0) resetGui();
		else updateGui();
	}
	
	
	private void resetGui() throws Exception
	{viewer.p(null);}
	
	
	private void updateGui() throws Exception
	{
		String text = (String) readFile.t(file);
		viewer.p(text);
	}
}