package a.entity.gus06.file.editor.notfound;

import a.framework.*;
import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20200429";}
	
	public static final Font FONT = new Font(Font.DIALOG,Font.BOLD,15);

	
	private JPanel panel;
	private JTextArea area;
	
	private File file;
	

	public EntityImpl() throws Exception
	{
		area = new JTextArea();
		area.setMargin(new Insets(5,5,5,5));
		area.setEditable(false);
		area.setBackground(Color.LIGHT_GRAY);
		area.setFont(FONT);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file!=null && file.exists()) throw new Exception("Existing file have been received: "+file);
		area.setText(message());
	}
	
	
	private String message()
	{
		if(file==null) return "NULL";
		return "File not found:\n"+file;
	}
}
