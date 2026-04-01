package a.entity.gus06.appli.gusdbmanager.gui.gui1.editor.area;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P, I, ActionListener {

	public String creationDate() {return "20150613";}


	private Service manager;
	

	private JScrollPane scroll;
	private JTextArea area;
	

	private String id;

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusdbmanager.connection.manager");
		
		area = new JTextArea();
		area.setEditable(false);
		area.setMargin(new Insets(3,3,3,3));
		
		scroll = new JScrollPane(area);
		
		manager.addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{updateGui();}



	public void p(Object obj) throws Exception
	{
		id = (String) obj;
		updateGui();
	}



	public Object i() throws Exception
	{return scroll;}


	
	private void updateGui()
	{
		try
		{
			String log = (String) manager.r("log_"+id);
			area.setText(log);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
}
