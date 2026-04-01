package a.entity.gus06.data.viewer.s1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, P, G, ActionListener {

	public String creationDate() {return "20140731";}


	private Service formPanel;
	private JTextField field;
	
	private S1 data;
	

	public EntityImpl() throws Exception
	{
		formPanel = Outside.service(this,"*gus06.swing.panel.formpanel");
	
		field = new JTextField();
		field.setEnabled(false);
		field.addActionListener(this);
		
		formPanel.v("send",field);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return formPanel.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		data = (S1) obj;
		field.setEnabled(data!=null);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{performSend();}
	
	
	
	private void performSend()
	{data.send(this,field.getText());}
}
