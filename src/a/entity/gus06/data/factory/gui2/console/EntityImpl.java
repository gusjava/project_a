package a.entity.gus06.data.factory.gui2.console;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import java.io.PrintStream;
import javax.swing.JScrollPane;

public class EntityImpl extends S1 implements Entity, I, P, V, Runnable {

	public String creationDate() {return "20191211";}


	private Service buildButton;
	private Service factory;
	private Service build;
	
	private JPanel panel;
	private JTextArea area;
	private JButton button;
	
	private PrintStream printStream;
	private P handler;
	


	public EntityImpl() throws Exception
	{
		buildButton = Outside.service(this,"gus06.swing.button.build.runnable");
		factory = Outside.service(this,"gus06.swing.textarea.factory.console1.black.white");
		build = Outside.service(this,"gus06.io.printstream.textarea3");
		
		area = (JTextArea) factory.i();
		printStream = (PrintStream) build.t(area);
		
		button = (JButton) buildButton.t(this);
		button.setText("Perform");
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		handler = (P) obj;
		area.setText("");
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
			area.setText("");
			handler.p(printStream);
			generated();
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
	
	
	private void generated()
	{send(this,"generated");}
}