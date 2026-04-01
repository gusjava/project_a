package a.entity.gus06.data.viewer.object.panel.class1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, I, P, ActionListener {

	public String creationDate() {return "20140820";}

	private Service viewer;
	
	private JPanel panel;
	private JButton button;
	
	private Class data;
	
	
	
	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.data.viewer.class1.lazy");
		
		button = new JButton("getClass()");
		button.addActionListener(this);
		button.setEnabled(false);
		
		panel = new JPanel(new BorderLayout());
		panel.add(button,BorderLayout.NORTH);
		panel.add((JComponent) viewer.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		data = obj==null?null:obj.getClass();
		button.setEnabled(data!=null);
		viewer.p(null);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{displayData();}
	
	
	
	private void displayData()
	{
		try{viewer.p(data);}
		catch(Exception e)
		{Outside.err(this,"displayData()",e);}
	}
}
