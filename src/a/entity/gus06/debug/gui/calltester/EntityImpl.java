package a.entity.gus06.debug.gui.calltester;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;


public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20140908";}

	private Service viewer;
	
	private JPanel panel;
	private JTextField field;
	
	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.data.viewer.object");
		
		field = new JTextField();
		field.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add((JComponent) viewer.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	private void perform()
	{
		try
		{
			String text = field.getText();
			field.setText("");
			
			Object result = Outside.resource(this,text);
			viewer.p(result);
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}

}
