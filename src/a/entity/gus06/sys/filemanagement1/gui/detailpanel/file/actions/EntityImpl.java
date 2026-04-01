package a.entity.gus06.sys.filemanagement1.gui.detailpanel.file.actions;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20240211";}
	
	public static final String KEY_NAME = "name";

	private Service show;
	private Service mapToLocation;
	private Service buildButton;
	private Service openFile;
	private Service viewFile;
	private Service copyFile;
	
	private JPanel panel;
	
	private JButton buttonCopyFile;
	private JButton buttonOpenFile;
	private JButton buttonViewFile;
	private JButton buttonOpenLocation;
	
	private Object engine;
	private Map selected;
	private Map prop;
	
	private Thread t;
	
	
	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.frame.show.data");
		mapToLocation = Outside.service(this,"gus06.sys.filemanagement1.tool.dirmap.findlocation");
		buildButton = Outside.service(this,"gus06.swing.button.build2.execute");
		openFile = Outside.service(this,"gus06.awt.desktop.open");
		viewFile = Outside.service(this,"gus06.swing.frame.show.file");
		copyFile = Outside.service(this,"gus06.clipboard.access.file");
		
		buttonCopyFile = build(this::startCopyFile,"FILE_clipboard#Copy file");
		buttonOpenFile = build(this::startOpenFile,"FILE_connectedBlue#Open file");
		buttonViewFile = build(this::startViewFile,"FILE_connectedGreen#View file");
		buttonOpenLocation = build(this::startOpenLocation,"dir_connectedBlue#Open location");
		
		JPanel panel_buttons = new JPanel(new GridLayout(0,1,5,5));
		panel_buttons.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel_buttons.add(buttonCopyFile);
		panel_buttons.add(buttonOpenFile);
		panel_buttons.add(buttonViewFile);
		panel_buttons.add(buttonOpenLocation);
		
		panel = cn(new JPanel(),panel_buttons);
		setButtonsEnabled(false);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	private JPanel cs(JComponent c, JComponent s)
	{
		JPanel p = new JPanel(new BorderLayout());
		if(c!=null) p.add(c,BorderLayout.CENTER);
		if(s!=null) p.add(s,BorderLayout.SOUTH);
		return p;
	}
	
	private JPanel cn(JComponent c, JComponent n)
	{
		JPanel p = new JPanel(new BorderLayout());
		if(c!=null) p.add(c,BorderLayout.CENTER);
		if(n!=null) p.add(n,BorderLayout.NORTH);
		return p;
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{
			engine = null;
			selected = null;
			prop = null;
			
			setButtonsEnabled(false);
			return;
		}
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		selected = (Map) o[1];
		prop = (Map) o[2];
		
		setButtonsEnabled(engine!=null && selected!=null);
	}
	
	
	private JButton build(E execute, String display) throws Exception
	{return (JButton) buildButton.t(new Object[]{execute,display});}
	
	
	
	private File retrieveLocation() throws Exception
	{
		try{return (File) mapToLocation.t(new Object[]{engine, selected});}
		catch(Exception e){showError(e.getMessage());}
		return null;
	}
	
	private File retrieveFile() throws Exception
	{
		File locationDir = retrieveLocation();
		if(locationDir==null) return null;
		
		String name = (String) selected.get(KEY_NAME);
		File file = new File(locationDir, name);
		if(!file.isFile())
		{
			showError("File not found: "+file);
			return null;
		}
		return file;
	}
	
	
	private void startCopyFile()
	{
		if(t!=null && t.isAlive()) return;
		Runnable r = this::performCopyFile;
		t = new Thread(r, "THREAD_"+getClass().getName());
		t.start();
	}
	
	private void startOpenFile()
	{
		if(t!=null && t.isAlive()) return;
		Runnable r = this::performOpenFile;
		t = new Thread(r, "THREAD_"+getClass().getName());
		t.start();
	}
	
	private void startViewFile()
	{
		if(t!=null && t.isAlive()) return;
		Runnable r = this::performViewFile;
		t = new Thread(r, "THREAD_"+getClass().getName());
		t.start();
	}
	
	private void startOpenLocation()
	{
		if(t!=null && t.isAlive()) return;
		Runnable r = this::performOpenLocation;
		t = new Thread(r, "THREAD_"+getClass().getName());
		t.start();
	}
	
	
	private void performCopyFile()
	{
		try
		{
			setButtonsEnabled(false);
			File file = retrieveFile();
			if(file!=null) copyFile.p(file);
		}
		catch(Exception e)
		{Outside.err(this,"performCopyFile()",e);}
		setButtonsEnabled(true);
	}
	
	private void performOpenFile()
	{
		try
		{
			setButtonsEnabled(false);
			File file = retrieveFile();
			if(file!=null) openFile.p(file);
		}
		catch(Exception e)
		{Outside.err(this,"performOpenFile()",e);}
		setButtonsEnabled(true);
	}
	
	private void performViewFile()
	{
		try
		{
			setButtonsEnabled(false);
			File file = retrieveFile();
			if(file!=null) viewFile.p(file);
		}
		catch(Exception e)
		{Outside.err(this,"performViewFile()",e);}
		setButtonsEnabled(true);
	}
	
	private void performOpenLocation()
	{
		try
		{
			setButtonsEnabled(false);
			File dir = retrieveLocation();
			if(dir!=null) openFile.p(dir);
		}
		catch(Exception e)
		{Outside.err(this,"performOpenLocation()",e);}
		setButtonsEnabled(true);
	}
	
	private void showError(String message)
	{
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private void setButtonsEnabled(boolean enabled)
	{
		buttonCopyFile.setEnabled(enabled);
		buttonOpenFile.setEnabled(enabled);
		buttonOpenLocation.setEnabled(enabled);
	}
	
}