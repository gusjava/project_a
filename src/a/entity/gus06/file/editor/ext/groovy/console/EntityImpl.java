package a.entity.gus06.file.editor.ext.groovy.console;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.io.File;
import java.io.PrintStream;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.text.JTextComponent;
import java.awt.Insets;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, P, ActionListener {

	public String creationDate() {return "20150706";}
	
	
	public static final Font FONT = new Font("Courier New", Font.PLAIN, 15);


	private Service compHolder;
	private Service process;

	private JPanel panel;
	private JTextComponent console;
	
	private Process proc;
	private File root;
	private S watch;
	
	private PrintStream p_out;
	private PrintStream p_err;
	
	

	public EntityImpl() throws Exception
	{
		compHolder = Outside.service(this,"*gus06.swing.textpane.holder.printstreamcomp");
		process = Outside.service(this,"gus06.process.holder1");
		
		console = (JTextComponent) compHolder.i();
		
		console.setMargin(new Insets(3,3,3,3));
		console.setBackground(Color.BLACK);
		console.setEditable(false);
		console.setFont(FONT);
		
		p_out = (PrintStream) compHolder.r("white");
		p_err = (PrintStream) compHolder.r("red");
		
		process.v("p_out",p_out);
		process.v("p_err",p_err);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(console),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{
			reset();
		}
		else if(obj instanceof File)
		{
			reset();
			executeFile((File) obj);
		}
		else if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			
			reset();
			executeFile((File)o[0],(String)o[1]);
		}
	}
	
	
	
	private void reset()
	{
		if(proc!=null) proc.destroy();
		console.setText("");
	}
	
	
	
	private void executeFile(File file)
	{
		try
		{
			root = file.getParentFile();
			String cmd = "groovy "+file.getName();
			sendCmd(cmd);
		}
		catch(Exception e)
		{Outside.err(this,"executeFile(File)",e);}
	}



	private void executeFile(File file, String s)
	{
		try
		{
			root = file.getParentFile();
			
			File f = new File(root,"tmp.groovy");
			PrintStream p = new PrintStream(f);
			p.print(s);
			p.close();
			
			sendCmd("groovy tmp.groovy");
		}
		catch(Exception e)
		{Outside.err(this,"executeFile(File,String)",e);}
	}
	
	
	
	
	
	private synchronized void sendCmd(String cmd) throws Exception
	{
		try{proc = Runtime.getRuntime().exec(cmd,null,root);}
		catch(Exception e)
		{
			e.printStackTrace(p_err);
			return;
		}
		if(watch!=null) watch.removeActionListener(this);
		watch = (S) process.t(proc);
		if(watch!=null) watch.addActionListener(this);
	}
	
	
	
	
	public void actionPerformed(ActionEvent e)
	{processOver();}
	
	private synchronized void processOver()
	{proc = null;}
}
