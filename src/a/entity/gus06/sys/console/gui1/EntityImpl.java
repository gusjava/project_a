package a.entity.gus06.sys.console.gui1;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import java.awt.Font;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Color;


public class EntityImpl implements Entity, I, V, R, ActionListener {

	public String creationDate() {return "20150329";}
	
	public static final Color COLOR_WAIT = new Color(204,255,255);
	public static final Color COLOR_IN = Color.LIGHT_GRAY;
	public static final Font FONT = new Font("Courier New", Font.PLAIN, 13);



	private Service compHolder;
	private Service fieldFactory;
	private Service buildProc;
	private Service process;
	private Service findColor;


	private JPanel panel;
	private JTextComponent console;
	private JTextField field;
	
	private PrintStream p_out;
	private PrintStream p_err;
	private PrintStream p_exit;
	private PrintStream p_bold;
	private PrintStream p_plain;
	
	private Process proc;
	private PrintStream proc_p;
	private S watch;
	private T cmdBuilder;
	private File root;
	
	private Color colorWait = COLOR_WAIT;
	private Color colorIn = COLOR_IN;
	
	
	
	

	public EntityImpl() throws Exception
	{
		compHolder = Outside.service(this,"*gus06.swing.textpane.holder.printstreamcomp");
		fieldFactory = Outside.service(this,"gus06.swing.textfield.factory.recallfield");
		buildProc = Outside.service(this,"gus06.runtime.exec1.cmd.inside");
		process = Outside.service(this,"gus06.process.holder1");
		findColor = Outside.service(this,"gus06.find.color");
		
		
		field = (JTextField) fieldFactory.i();
		field.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{sendCmd(input());}
		});
		
		console = (JTextComponent) compHolder.i();
		console.setMargin(new Insets(3,3,3,3));
		console.setEditable(false);
		console.setFont(FONT);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(new JScrollPane(console),BorderLayout.CENTER);
		
		p_out = (PrintStream) compHolder.r("blue");
		p_err = (PrintStream) compHolder.r("red");
		p_exit = (PrintStream) compHolder.r("green.darker");
		p_bold = (PrintStream) compHolder.r("bold black");
		p_plain = (PrintStream) compHolder.r("black");
		
		process.v("p_out",p_out);
		process.v("p_err",p_err);
		process.v("p_exit",p_exit);
		
		updateBackground();
	}
	
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	
	private void updateBackground()
	{
		setBackground(proc==null?colorWait:colorIn);
	}
	
	private void setBackground(Color c)
	{
		field.setBackground(c);
		console.setBackground(c);
	}
	
	
	
	
	private String input()
	{
		String text = field.getText();
		field.setText("");
		return text;
	}
	
	
	
	
	private void sendCmd(String cmd)
	{
		try
		{
			if(cmd.equals("kill")) {killProcess();return;}
			if(cmd.equals("clear")) {clearConsole();return;}
			if(cmd.equals("root")) {displayRoot();return;}
			
			if(proc!=null) inputProcess(cmd);
			else startNewProcess(cmd);
		}
		catch(Exception e)
		{Outside.err(this,"sendCmd(String)",e);}
	}
	
	
	
	
	
	private synchronized void inputProcess(String command) throws Exception
	{
		p_plain.println(command);
		proc_p.println(command);
	}
	
	
	
	private synchronized void startNewProcess(String cmd) throws Exception
	{
		if(cmdBuilder!=null) cmd = (String) cmdBuilder.t(cmd);
		p_bold.println(cmd);
		
		try{proc = buildProc(cmd);}
		catch(Exception e)
		{
			e.printStackTrace(p_err);
			return;
		}
		
		proc_p = new PrintStream(proc.getOutputStream());
		updateBackground();
		
		if(watch!=null) watch.removeActionListener(this);
		watch = (S) process.t(proc);
		if(watch!=null) watch.addActionListener(this);
	}
	
	
	
	
	private Process buildProc(String cmd) throws Exception
	{return (Process) buildProc.t(new Object[]{cmd,root});}
	
	
	
	
	
	public void actionPerformed(ActionEvent e)
	{processOver();}
	
	
	
	
	private synchronized void processOver()
	{
		proc_p.close();
		proc_p = null;
		proc = null;
		
		updateBackground();
		Toolkit.getDefaultToolkit().beep();
	}
	
	
	
	
	private synchronized void killProcess()
	{proc.destroy();}
	
	private synchronized void clearConsole()
	{console.setText("");}
	
	private synchronized void displayRoot()
	{p_bold.println(root==null?"null":root.getAbsolutePath());}
	
	
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("cmdBuilder")) {cmdBuilder = (T) obj;return;}
		if(key.equals("sendCmd")) {sendCmd((String) obj);return;}
		if(key.equals("root")) {root = (File) obj;return;}
		
		if(key.equals("colorWait"))
		{
			colorWait = (Color) findColor.t(obj);
			updateBackground();
			return;
		}
		if(key.equals("colorIn"))
		{
			colorIn = (Color) findColor.t(obj);
			updateBackground();
			return;
		}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("root")) return root;
		if(key.equals("field")) return field;
		if(key.equals("console")) return console;
		if(key.equals("keys")) return new String[]{"root","field","console"};
		
		throw new Exception("Unknown key: "+key);
	}
}
