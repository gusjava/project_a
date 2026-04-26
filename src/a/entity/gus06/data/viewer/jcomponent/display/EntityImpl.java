package a.entity.gus06.data.viewer.jcomponent.display;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, I, P, ActionListener {

	public String creationDate() {return "20140808";}


	private Service shiftPanel;
	
	private JPanel panel;
	private JButton button;
	
	private JComponent data;
	
	
	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		
		button = new JButton("Display");
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(button,BorderLayout.NORTH);
		panel.add((JComponent) shiftPanel.i(),BorderLayout.CENTER);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (JComponent) obj;
		button.setEnabled(data!=null);
		shiftPanel.p(null);
	}
	
	public void actionPerformed(ActionEvent e)
	{showData();}
	
	
	private void showData()
	{
		try{shiftPanel.p(data);}
		catch(Exception e){Outside.err(this,"showData()",e);}
	}
}
