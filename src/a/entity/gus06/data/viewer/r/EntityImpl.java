package a.entity.gus06.data.viewer.r;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, I, P, G, ActionListener {

	public String creationDate() {return "20140731";}

	private Service viewer;
	
	private JPanel panel;
	private JTextField field;
	
	private R data;

	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.data.viewer.object");
		
		field = new JTextField();
		field.setEnabled(false);
		field.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add((JComponent) viewer.i(),BorderLayout.CENTER);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (R) obj;
		field.setEnabled(data!=null);
		viewer.p(null);
	}
	
	public void actionPerformed(ActionEvent e)
	{showData();}
	
	
	private void showData()
	{
		try
		{
			Object result = data.r(field.getText());
			viewer.p(result);
			field.setText("");
		}
		catch(Exception e){Outside.err(this,"showData()",e);}
	}
}
