package a.entity.gus06.jna.keyboard.activity;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, F {

	public String creationDate() {return "20170409";}
	
	public static long WAIT = 30000;


	private Service support;

	private long last = -1;


	public EntityImpl() throws Exception
	{
		support = Outside.service(this,"gus06.jna.keyboard.support");
		support.addActionListener(this);
	}
	
	
	public synchronized boolean f(Object obj) throws Exception
	{
		long t = System.currentTimeMillis();
		return t-last<WAIT;
	}


	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	private synchronized void perform()
	{last = System.currentTimeMillis();}
}
