package a.entity.gus06.data.viewer.p;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, I, P, G, ActionListener {

	public String creationDate() {return "20140731";}


	private JPanel panel;
	private JButton button;
	
	private P data;
	

	public EntityImpl() throws Exception
	{
		button = new JButton("Call p() with String");
		button.setEnabled(false);
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(button,BorderLayout.NORTH);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (P) obj;
		button.setEnabled(data!=null);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	private void perform()
	{
		try
		{
			String info = JOptionPane.showInputDialog("");
			if(info!=null) data.p(info);
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
}
