package a.entity.gus06.socket.clientgui.socketviewer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import a.framework.*;
import java.awt.Insets;

public class EntityImpl implements Entity, ActionListener, I, P {

	public String creationDate() {return "20180312";}


	private Service buildHolder;
	private Service supportHolder;
	
	private Socket socket;
	private Object holder;
	private Thread t;
	
	private JTextArea textArea;
	private JTextField field;
	private JPanel panel;
	
	
	
	public EntityImpl() throws Exception
	{
		buildHolder = Outside.service(this,"gus06.socket.build.holder");
		supportHolder = Outside.service(this,"*gus06.support.holder");
		
		field = new JTextField();
		field.setEnabled(false);
		field.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){send();}
		});
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setMargin(new Insets(3,3,3,3));
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(textArea),BorderLayout.CENTER);
		panel.add(field,BorderLayout.SOUTH);
		
		supportHolder.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		socket = (Socket) obj;
		field.setEnabled(socket!=null);
		
		holder = buildHolder.t(socket);
		supportHolder.p(holder);
		
		if(holder!=null)
		{
			t = new Thread((Runnable) holder,"THREAD_"+getClass().getName());
			t.start();
		}
	}
	

	
	
	private void send()
	{
		try
		{
			if(holder==null) return;
			String line = field.getText();
			field.setText("");
			
			((P) holder).p(line);
			println(">"+line);
		}
		catch(Exception e)
		{Outside.err(this,"send()",e);}
	}


	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		
		if(s.equals("connectionStarted()")) {println("CONNECTION STARTED");return;}
		if(s.equals("connectionClosed()")) {println("CONNECTION CLOSED");return;}
		if(s.equals("connectionLost()")) {println("CONNECTION LOST");return;}
		if(s.equals("messageSent()")) {return;}
		if(s.equals("messageReceived()")) {messageReceived();return;}
	}

	
	private void messageReceived()
	{
		try
		{
			if(holder==null) return;
			String line = (String) ((R) holder).r("lastreceivedline");
			println("<"+line);
		}
		catch(Exception e)
		{Outside.err(this,"messageReceived()",e);}
	}
	
	
	private void println(String line)
	{
		textArea.append(line+"\n");
		textArea.setCaretPosition(textArea.getDocument().getLength());
		
	}
}
