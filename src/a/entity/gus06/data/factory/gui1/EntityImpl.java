package a.entity.gus06.data.factory.gui1;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JComponent;

public class EntityImpl extends S1 implements Entity, I, P, G, V, Runnable {

	public String creationDate() {return "20191210";}


	private Service buildButton;
	private Service viewer;
	
	private JPanel panel;
	private JButton button;
	
	private G factory;
	private Object data;


	public EntityImpl() throws Exception
	{
		buildButton = Outside.service(this,"gus06.swing.button.build.runnable");
		viewer = Outside.service(this,"*gus06.data.viewer.object");
		
		button = (JButton) buildButton.t(this);
		button.setText("Perform");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) viewer.i(),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public void p(Object obj) throws Exception
	{
		factory = (G) obj;
		data = null;
	}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("buttonText")) {button.setText((String) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void run()
	{
		try
		{
			data = factory.g();
			viewer.p(data);
			generated();
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
	
	
	private void generated()
	{send(this,"generated");}
}
