package a.entity.gus06.debug.gui.errgenerator;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;


public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20221117";}
	
	private JPanel panel;
	private JTextField field;
	
	public EntityImpl() throws Exception
	{
		field = new JTextField();
		field.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
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
			throw new Exception(text);
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}

}