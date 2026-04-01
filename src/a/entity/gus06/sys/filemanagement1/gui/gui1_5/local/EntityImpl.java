package a.entity.gus06.sys.filemanagement1.gui.gui1_5.local;

import a.framework.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import java.io.File;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20191226";}


	private Service tab;
	private Service buildButton;
	private Service openDir;
	
	private JPanel panel;
	private JButton button_open;

	private Object engine;
	
	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		buildButton = Outside.service(this,"gus06.swing.button.build2.execute");
		openDir = Outside.service(this,"gus06.awt.desktop.open");
		
		button_open = build(this::open,"ACTION_openDir#Open dir");
		
		JPanel panel_buttons = new JPanel(new GridLayout(1,0,5,5));
		panel_buttons.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		panel_buttons.add(button_open);
		
		panel = cs((JComponent) tab.i(),panel_buttons);
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		engine = obj;
	}
	
	
	private JButton build(E execute, String display) throws Exception
	{return (JButton) buildButton.t(new Object[]{execute,display});}
	
	
	
	private void open()
	{
		try
		{
			if(engine==null) return;
			
			File dirLocal = (File) ((R) engine).r("dirLocal");
			openDir.p(dirLocal);
		}
		catch(Exception e)
		{Outside.err(this,"open()",e);}
	}
	
	
	
	private JPanel cs(JComponent c, JComponent s)
	{
		JPanel p = new JPanel(new BorderLayout());
		p.add(c,BorderLayout.CENTER);
		p.add(s,BorderLayout.SOUTH);
		return p;
	}
}