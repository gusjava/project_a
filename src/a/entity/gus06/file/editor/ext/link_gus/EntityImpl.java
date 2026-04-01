package a.entity.gus06.file.editor.ext.link_gus;

import a.framework.*;
import java.io.File;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, I, P, G {

	public String creationDate() {return "20250605";}

	
	private Service readFile;
	private Service writeFile;
	private Service mainEditor;
	private Service fieldActions;
	private Service fileProvider;
	private Service shiftPanel;
	
	private File file;
	private JTextField field;


	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string");
		writeFile = Outside.service(this,"gus06.file.write.string");
		mainEditor = Outside.service(this,"*gus06.file.editor.main");
		fieldActions = Outside.service(this,"gus06.swing.textfield.cust.actions1");
		fileProvider = Outside.service(this,"m102.r.fileprovider");
		shiftPanel = Outside.service(this,"*gus06.swing.panel.shiftpanel");
		
		field = new JTextField();
		field.addActionListener(this);
		
		fieldActions.p(field);
		initWithField();
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
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
		field.setText("");
		mainEditor.p(null);
		initWithField();
	}
	
	private void updateGui() throws Exception
	{
		String path = (String) readFile.t(file);
		field.setText(path);
		updateGui(path);
	}


	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	private void perform()
	{
		try
		{
			if(file==null) return;
			
			String path = field.getText();
			writeFile.p(new Object[]{file, path});
			updateGui(path);
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}

	
	private void updateGui(String path) throws Exception
	{
		boolean hideField = path.startsWith("#");
		if(hideField) path = path.substring(1);
		
		File target = (File) fileProvider.r(path);
		mainEditor.p(target);
		
		if(hideField) initWithoutField();
		else initWithField();
	}
	
	private void initWithField() throws Exception
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add((JComponent) mainEditor.i(),BorderLayout.CENTER);
		shiftPanel.p(panel);
	}
	
	private void initWithoutField() throws Exception
	{
		shiftPanel.p(mainEditor.i());
	}
}
