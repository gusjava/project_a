package a.entity.gus06.appli.gusclient1.alert.toolbar;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20140808";}


	private Service clearOnClick;
	private Service alertManager;

	private JLabel label;

	public EntityImpl() throws Exception
	{
		alertManager = Outside.service(this,"gus06.appli.gusclient1.alert.manager");
		clearOnClick = Outside.service(this,"gus06.swing.label.cust.onclick.clear");
		
		label = new JLabel(" ");
		clearOnClick.p(label);
	
		alertManager.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	
		
	public void actionPerformed(ActionEvent e)
	{displayAlert();}
	
	
	private void displayAlert()
	{
		try
		{
			String name = (String) alertManager.r("name");
			Object src = alertManager.r("src");
		
			label.setText(name);
		}
		catch(Exception e)
		{Outside.err(this,"displayAlert()",e);}
	}
}
