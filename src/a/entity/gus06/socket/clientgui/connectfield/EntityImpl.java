package a.entity.gus06.socket.clientgui.connectfield;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import javax.swing.JTextField;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, I, G, R, Runnable, ActionListener {

	public String creationDate() {return "20180312";}

	public static final String INITVALUE = "127.0.0.1:4000";

	
	private Service build;
	private Service persist;
	
	private JTextField field;
	private Thread t;
	private Socket socket;
	private Exception e;
	
	
	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.socket.builder1");
		persist = Outside.service(this,"gus06.app.persister1.manager.swing");
		
		field = new JTextField(INITVALUE);
		field.addActionListener(this);
		
		persist.v(getClass().getName()+"_text",field);
	}
	
	
	
	public Object i() throws Exception
	{return field;}
	
	public Object g() throws Exception
	{return socket;}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("exception")) return e;
		if(key.equals("keys")) return new String[]{"exception"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void actionPerformed(ActionEvent evt)
	{connect();}
	
	
	private void connect()
	{
		if(t!=null && t.isAlive()) return;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public void run()
	{
		try
		{
			connecting();
			
			String s = field.getText();
           		socket = (Socket) build.t(s);

			connected();
		}
		catch(Exception e)
		{
			this.e = e;
			failed();
		}
	}
	
	
	
	private void connecting()
	{send(this,"connecting()");}
	
	private void connected()
	{send(this,"connected()");}
	
	private void failed()
	{send(this,"failed()");}
}
