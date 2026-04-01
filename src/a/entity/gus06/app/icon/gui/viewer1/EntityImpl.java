package a.entity.gus06.app.icon.gui.viewer1;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20201206";}


	private Service viewer;

	private JPanel panel;
	private JButton button;
	
	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.app.icon.gui.viewer");
		
		button = new JButton("Refresh");
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) viewer.i(),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void actionPerformed(ActionEvent e)
	{refresh();}
	
	
	private void refresh()
	{
		try{viewer.e();}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
}
