package a.entity.gus06.sys.gusappmonitor.client.init;

import a.framework.*;
import java.net.Socket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener {

	public String creationDate() {return "20190312";}
	
	public static final String INFO = "localhost:4567";
	
	public static final String KEY_MONITORING_ENABLED = "app.monitoring.enabled";


	private Service builder;
	private Service buildHolder;
	private Service handleMessage;
	private Service started;
	private Service propBoolDF;
	
	private S holder;
	private Thread t;


	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.socket.builder1");
		buildHolder = Outside.service(this,"gus.x.socket.build.holder");
		handleMessage = Outside.service(this,"gus06.sys.gusappmonitor.client.handlemessage");
		started = Outside.service(this,"gus06.sys.gusappmonitor.client.started");
		propBoolDF = Outside.service(this,"propbool_df");
		
		if(propBoolDF.f(KEY_MONITORING_ENABLED)) connect();
	}
	
	
	private void connect()
	{
		try
		{
			Socket socket = (Socket) builder.t(INFO);
			holder = (S) buildHolder.t(socket);
			holder.addActionListener(this);
			
			t = new Thread((Runnable) holder,"THREAD_"+getClass().getName());
			t.start();
		}
		catch(Exception e)
		{Outside.err(this,"connect()",e);}
	}



	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("connectionStarted()")) connectionStarted();
		if(s.equals("messageReceived()")) messageReceived();
	}


	private void connectionStarted()
	{
		try{started.p(holder);}
		catch(Exception e)
		{Outside.err(this,"connectionStarted()",e);}
	}
	
	private void messageReceived()
	{
		try{handleMessage.p(holder);}
		catch(Exception e)
		{Outside.err(this,"messageReceived()",e);}
	}
}