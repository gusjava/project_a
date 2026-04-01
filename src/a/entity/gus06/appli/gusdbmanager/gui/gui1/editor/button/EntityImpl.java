package a.entity.gus06.appli.gusdbmanager.gui.gui1.editor.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import a.framework.*;
import javax.swing.JButton;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P, I, ActionListener {

	public String creationDate() {return "20150613";}




	private Service trigger;
	private Service manager;
	private Service statusColor;

	private JButton button;

	private String id;

	
	
	public EntityImpl() throws Exception
	{
		trigger = Outside.service(this,"gus06.appli.gusdbmanager.connection.trigger");
		manager = Outside.service(this,"gus06.appli.gusdbmanager.connection.manager");
		statusColor = Outside.service(this,"gus06.appli.gusdbmanager.connection.statuscolor");
		
		button = new JButton("Connect");
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {connect();}
		});
		
		manager.addActionListener(this);
	}
	
	

	public void actionPerformed(ActionEvent e)
	{updateGui();}



	public void p(Object obj) throws Exception
	{
		id = (String) obj;
		
		button.setEnabled(id!=null);
		updateGui();
	}



	public Object i() throws Exception
	{return button;}


	
	
	private void connect()
	{
		try
		{
			if(id==null) return;
			trigger.p(id);
		}
		catch(Exception e)
		{Outside.err(this,"connect()",e);}
	}
	
	
	
	
	private void updateGui()
	{
		try
		{
			String status = (String) manager.r("status_"+id);
			Color color = (Color) statusColor.t(status);
			button.setForeground(color);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
}
