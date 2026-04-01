package a.entity.gus06.data.viewer.g;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, I, P, G, ActionListener {

	public String creationDate() {return "20140731";}

	private Service viewer;
	
	private JPanel panel;
	private JButton button;
	
	private G data;
	
	
	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.data.viewer.object");
		
		button = new JButton("Call g()");
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(button,BorderLayout.NORTH);
		panel.add((JComponent) viewer.i(),BorderLayout.CENTER);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (G) obj;
		button.setEnabled(data!=null);
		viewer.p(null);
	}
	
	public void actionPerformed(ActionEvent e)
	{showData();}
	
	
	private void showData()
	{
		try{viewer.p(data.g());}
		catch(Exception e){Outside.err(this,"showData()",e);}
	}
}
