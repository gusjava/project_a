package a.entity.gus06.swing.tabbedpane.build.closeable.demo;

import a.framework.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20141203";}


	private Service closeable;
	private JPanel panel;
	private JTextField textField;
	
	
	public EntityImpl() throws Exception
	{
		closeable = Outside.service(this,"gus06.swing.tabbedpane.build.closeable");
	
		textField = new JTextField();
		textField.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(textField,BorderLayout.NORTH);
		panel.add((JComponent) closeable.i(),BorderLayout.CENTER);
		
		closeable.v("acceptClose",new F(){
			public boolean f(Object obj) throws Exception
			{
				int r = JOptionPane.showConfirmDialog(null,"Close tab ?");
				return r==JOptionPane.YES_OPTION;
			}
		});
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void actionPerformed(ActionEvent e)
	{addComponent();}
	
	
	
	private void addComponent()
	{
		try
		{
			String name = textField.getText();
			JTextArea area = new JTextArea("area for "+name);
			closeable.v(name,new JScrollPane(area));
		}
		catch(Exception e)
		{Outside.err(this,"addComponent()",e);} 
	}

}
