package a.entity.gus06.data.viewer.method.value;

import a.framework.*;
import javax.swing.JComponent;
import java.lang.reflect.Method;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, I, P, G, ActionListener {

	public String creationDate() {return "20140821";}

	private Service viewer;
	private Service getReturn;
	
	private JPanel panel;
	private JButton button;
	
	private Method data;
	
	
	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.data.viewer.object.lazy");
		getReturn = Outside.service(this,"gus06.reflection.method.invoke.static1");
		
		button = new JButton("Show method's return value");
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
		data = (Method) obj;
		button.setEnabled(data!=null);
		viewer.p(null);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{displayData();}
	
	
	
	private void displayData()
	{
		try{viewer.p(getReturn(data));}
		catch(Exception e)
		{Outside.err(this,"displayData()",e);}
	}
		
	
	
	
	private Object getReturn(Method m) throws Exception
	{
		if(m==null) return null;
		return getReturn.t(m);
	}
}
