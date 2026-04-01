package a.entity.gus06.file.editor.ext.db.unsupported;

import java.awt.BorderLayout;
import java.io.File;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250723";}

	
	private JPanel panel;
	private JLabel label;
	
	private File file;
	
	public EntityImpl() throws Exception
	{
		label = new JLabel("Not supported yet");
		label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		panel = new JPanel(new BorderLayout());
		panel.add(label, BorderLayout.CENTER);
	}
	
	public Object i() throws Exception
	{return panel;}
	
	public void p(Object obj) throws Exception
	{file = (File) obj;}
}
