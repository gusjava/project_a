package a.entity.gus06.appli.gusclient1.gui.console.perform;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import a.framework.*;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl extends S1 implements Entity, P {

	public String creationDate() {return "20140804";}
	
	
    
	private Service serverOut;
	private Service executeCmd;
	
	private Thread t;
	
	
	
	public EntityImpl() throws Exception
	{
		serverOut = Outside.service(this,"gus06.sys.gusserver1.printstream");
		executeCmd = Outside.service(this,"gus06.command.execute");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String line = (String) o[0];
		PrintStream out = (PrintStream) o[1];
	
		if(t!=null && t.isAlive()) return;
		
		t = new Thread(new Holder(line,out),"THREAD_"+getClass().getName());
		t.start();
	}
	
		
		
	
	private class Holder implements Runnable
	{
		private String line;
		private PrintStream out;
	
		public Holder(String line, PrintStream out)
		{this.line = line;this.out = out;}
		
		public void run()
		{
			send_start();
			handleLine(line,out);
			send_end();
		}
	}
	
	
	
		
	
	private void handleLine(String line, PrintStream out)
	{
		try
		{
			if(line.equals("exit")) System.exit(0);
			
			out.println(now()+">"+line);
			
			serverOut.p(out);
			executeCmd(line,out);
			serverOut.p(null);
			
			out.println("___________");
		}
		catch(Exception e)
		{Outside.err(this,"handleLine(String,PrintStream)",e);}
	}
	
	
	
	
	private void executeCmd(String line, PrintStream out)
	{
		try{executeCmd.p(line);}
		catch(Exception e)
		{
			Outside.err(this,"executeCmd(String,PrintStream)",e);
			out.println("Error:"+e.getMessage());
		}
	}
	
	
	
	
	
	private void send_start()
	{send(this,"start()");}
			
	private void send_end()
	{send(this,"end()");}
	
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	private String now() {return sdf.format(new Date());}
}
