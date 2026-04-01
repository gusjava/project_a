package a.entity.gus06.data.viewer.class1.instanciator;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, P, G, ActionListener {

	public String creationDate() {return "20140806";}

	private Service viewer;

	private JPanel panel;
	private JButton button;
	
	private Class data;
	
	
	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.data.viewer.object.lazy");
		
		button = new JButton("Instanciate");
		button.setEnabled(false);
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
		data = (Class) obj;
		button.setEnabled(data!=null);
		viewer.p(null);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{instanciateData();}
	
	
	private void instanciateData()
	{
		try{viewer.p(data.newInstance());}
		catch(Exception e){Outside.err(this,"instanciateData()",e);}
	}
}
