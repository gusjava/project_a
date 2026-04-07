package a.entity.gus06.appli.gusappmonitor.handlesocket;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190312";}
	
	//received messages
	public static final String INIT = "INIT";
	public static final String AWT = "AWT";
	public static final String DEBUG = "DEBUG";
	
	//sent events
	public static final String EVENT = "EVENT";
	public static final String EVENT_LOST = "lost";
	public static final String EVENT_CLOSED = "closed";
	
	private Service buildThread;
	private Service buildHolder;
	private Service manager;
	
	public EntityImpl() throws Exception
	{
		buildThread = Outside.service(this,"gus.x.thread.wrap1");
		buildHolder = Outside.service(this,"gus.x.socket.wrap1");
		manager = Outside.service(this,"gus06.appli.gusappmonitor.manager");
	}
	
	
	public void p(Object obj) throws Exception
	{
		S sup = (S) buildHolder.t(obj);
		new Holder(sup);
	}
	
	
	private class Holder implements ActionListener, P
	{
		private S sup;
		private Thread t;
		private Object config;
		private boolean initialized;
		
		public Holder(S sup) throws Exception
		{
			this.sup = sup;
			sup.addActionListener(this);
			initialized = false;
			
			t = (Thread) buildThread.t((Runnable) sup);
			t.start();
		}
		
		
		public void p(Object obj) throws Exception
		{((P)sup).p(obj);}
		
		
		public void actionPerformed(ActionEvent e)
		{
			String s = e.getActionCommand();
			
			if(s.equals("messageReceived()")) messageReceived();
			else if(s.equals("connectionLost()")) connectionLost();
			else if(s.equals("connectionClosed()")) connectionClosed();
		}
	
		private void messageReceived()
		{
			try
			{
				String message = (String) ((G)sup).g();
				if(message.contains(":"))
				{
					String[] nn = message.split(":",2);
					String key = nn[0];
					String value = nn[1];
					
					if(key.equals(INIT)) handleInit(value);
					else if(key.equals(AWT)) handleAwt(value);
					else if(key.equals(DEBUG)) handleDebug(value);
				}
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"messageReceived()",e);}
		}
		
		
		private void handleInit(String value) throws Exception
		{
			if(initialized) throw new Exception("Already initialized");
			config = manager.t(new Object[]{value,this});
			initialized = true;
		}
		
		private void handleAwt(String value) throws Exception
		{
			try
			{if(config!=null) ((V) config).v(AWT,value);}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"handleAwt(String)",e);}
		}
		
		private void handleDebug(String value) throws Exception
		{
			try
			{if(config!=null) ((V) config).v(DEBUG,value);}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"handleDebug(String)",e);}
		}
		
		
		private void connectionLost()
		{
			try
			{if(config!=null) ((V) config).v(EVENT,EVENT_LOST);}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"connectionLost()",e);}
		}
		
		private void connectionClosed()
		{
			try
			{if(config!=null) ((V) config).v(EVENT,EVENT_CLOSED);}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"connectionClosed()",e);}
		}
	}
}
