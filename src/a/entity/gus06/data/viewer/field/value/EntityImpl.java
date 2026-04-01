package a.entity.gus06.data.viewer.field.value;

import a.framework.*;
import javax.swing.JComponent;
import java.lang.reflect.Field;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, I, P, G, ActionListener {

	public String creationDate() {return "20140821";}

	private Service viewer;
	private Service getValue;
	
	private JPanel panel;
	private JButton button;
	
	private Field data;
	
	
	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.data.viewer.object.lazy");
		getValue = Outside.service(this,"gus06.reflection.field.getstaticvalue");
		
		button = new JButton("Show field's value");
		button.addActionListener(this);
		button.setEnabled(false);
		
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
		data = (Field) obj;
		button.setEnabled(data!=null);
		viewer.p(null);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{displayData();}
	
	
	
	private void displayData()
	{
		try{viewer.p(getValue(data));}
		catch(Exception e)
		{Outside.err(this,"displayData()",e);}
	}
		
	
	
	
	private Object getValue(Field f) throws Exception
	{
		if(f==null) return null;
		return getValue.t(f);
	}
}
