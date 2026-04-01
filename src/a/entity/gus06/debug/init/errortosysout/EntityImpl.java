package a.entity.gus06.debug.init.errortosysout;

import a.framework.*;

import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.List;


public class EntityImpl implements Entity, ActionListener, Runnable {

	public String creationDate() {return "20140913";}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	private String now() {return sdf.format(new Date());}
	
	
	private List errors;
	private ArrayBlockingQueue queue;
	private Thread t;
	
	private PrintStream out;


	public EntityImpl() throws Exception
	{
		errors = (List) Outside.resource(this,"errlist");
		out = (PrintStream) Outside.resource(this,"sysout");
		queue = new ArrayBlockingQueue(100);
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
		
		((S) errors).addActionListener(this);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{enqueueLastErr();}
	
	
	
	public void enqueueLastErr()
	{
		try
		{
			Object[] o = (Object[]) errors.get(errors.size()-1);
			if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
			queue.put(o);
		}
		catch(Exception e)
		{Outside.err(this,"enqueueLastErr()",e);}
	}
	
	
	
	
	public void run()
	{
		while(true) perform();
	}
	
	private void perform()
	{
		try
		{
			Object[] o = (Object[]) queue.take();
			printErr(o);
		}
		catch(InterruptedException e) {}
	}
	
	
	private void printErr(Object[] o)
	{
		try
		{
			Entity entity = (Entity) o[0];
			String id = (String) o[1];
			Date date = (Date) o[2];
			Exception exp = (Exception) o[3];
	
			String src = entity.getClass().getName()+"@"+id;
			String timeStamp = sdf.format(date);
		
			println("___________________");
			println("time:"+timeStamp);
			println("src:"+src);
			println("");
			
			exp.printStackTrace(out);
		}
		catch(Exception e)
		{e.printStackTrace();}
	}
	
	
	private void println(String m)
	{out.println(m);}
}
