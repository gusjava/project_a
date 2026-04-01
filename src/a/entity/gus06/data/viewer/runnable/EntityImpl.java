package a.entity.gus06.data.viewer.runnable;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, P, G, ActionListener {

	public String creationDate() {return "20140731";}


	private JPanel panel;
	private JButton button;
	
	private Runnable data;
	

	public EntityImpl() throws Exception
	{
		button = new JButton("Start runnable");
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
		data = (Runnable) obj;
		button.setEnabled(data!=null);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{startData();}
	
	
	private void startData()
	{
		if(data==null) return;
		new Thread(data,"THREAD_"+getClass().getName()).start();
	}
}
