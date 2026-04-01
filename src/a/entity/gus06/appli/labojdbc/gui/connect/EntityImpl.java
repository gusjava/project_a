package a.entity.gus06.appli.labojdbc.gui.connect;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;


public class EntityImpl extends S1 implements Entity, ActionListener, I, G, Runnable {

	public String creationDate() {return "20150621";}


	private Service guiConnect;
	private Service buildCxHolder;


	private JPanel panel;
	private JTextArea area;
	private JButton button;
	
	private Object holder;
	private Thread t;
	
	

	public EntityImpl() throws Exception
	{
		guiConnect = Outside.service(this,"*gus06.jdbc.gui.connect1");
		buildCxHolder = Outside.service(this,"gus06.jdbc.connection.holder");
		
		holder = buildCxHolder.t(guiConnect);
		
		button = new JButton("Connect");
		button.addActionListener(this);
		
		area = new JTextArea();
		area.setEditable(false);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) guiConnect.i(),BorderLayout.NORTH);
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return holder;}


	public void actionPerformed(ActionEvent e)
	{connect();}
	
	
	
	
	private void connect()
	{
		if(t!=null && t.isAlive()) return;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	
	
	public void run()
	{
		button.setForeground(Color.ORANGE);
		button.setText("Connecting...");
		
		perform();
		
		button.setForeground(Color.BLACK);
		button.setText("Connect");
	}
	
	
	
	
	
	
	private void perform()
	{
		try
		{
			area.setText("");
			((P) holder).p("reset");
			((G) holder).g();
			
			area.setText("Connected");
			connected();
		}
		catch(Exception e)
		{
			Outside.err(this,"perform()",e);
			area.setText(e.toString());
		}
	}



	private void connected()
	{send(this,"connected()");}
}
