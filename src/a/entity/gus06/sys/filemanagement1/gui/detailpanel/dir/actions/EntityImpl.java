package a.entity.gus06.sys.filemanagement1.gui.detailpanel.dir.actions;

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

	public String creationDate() {return "20250610";}

	private Service show;
	private Service mapToLocation;
	private Service buildButton;
	private Service openFile;
	private Service viewFile;
	
	private JPanel panel;
	
	private JButton buttonOpenLocation;
	private JButton buttonViewLocation;
	
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
		
		buttonOpenLocation = build(this::startOpenLocation,"dir_connectedBlue#Open location");
		buttonViewLocation = build(this::startViewLocation,"dir_connectedRed#View location");
		
		JPanel panel_buttons = new JPanel(new GridLayout(0,1,5,5));
		panel_buttons.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel_buttons.add(buttonOpenLocation);
		panel_buttons.add(buttonViewLocation);
		
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
			
			setButtonsEnabled(false);
			return;
		}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		selected = (Map) o[1];
		
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
	
	private void startOpenLocation()
	{
		if(t!=null && t.isAlive()) return;
		Runnable r = this::performOpenLocation;
		t = new Thread(r, "THREAD_"+getClass().getName());
		t.start();
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
	
	
	private void startViewLocation()
	{
		if(t!=null && t.isAlive()) return;
		Runnable r = this::performViewLocation;
		t = new Thread(r, "THREAD_"+getClass().getName());
		t.start();
	}
	
	private void performViewLocation()
	{
		try
		{
			setButtonsEnabled(false);
			File dir = retrieveLocation();
			if(dir!=null) viewFile.p(dir);
		}
		catch(Exception e)
		{Outside.err(this,"performViewLocation()",e);}
		setButtonsEnabled(true);
	}
	
	private void showError(String message)
	{
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private void setButtonsEnabled(boolean enabled)
	{
		buttonOpenLocation.setEnabled(enabled);
		buttonViewLocation.setEnabled(enabled);
	}
	
}