package a.entity.gus06.file.editor.ext.jar.viewer.dependencies;

import a.framework.*;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.util.jar.Manifest;
import java.util.List;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, I, P {

	public String creationDate() {return "20170223";}


	private Service find;
	private Service listToString;


	private JTextArea area;
	private JButton button;
	private JPanel panel;
	
	private File file;
	

	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.file.jar.imports.listing.dep");
		listToString = Outside.service(this,"gus06.tostring.list");
		
		area = new JTextArea();
		area.setEditable(false);
		area.setMargin(new Insets(3,3,3,3));
		
		button = new JButton("Display dependencies");
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		button.setEnabled(file!=null);
		area.setText("");
	}



	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	private void perform()
	{
		try
		{
			if(file==null) return;
			
			List list = (List) find.t(file);
			String s = (String) listToString.t(list);
			area.setText(s);
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
}
