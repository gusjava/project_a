package a.entity.gus06.appli.console.gui.maingui;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;

import javax.swing.JPanel;
import javax.swing.JButton;
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
import javax.swing.JComponent;



public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20150323";}
	
	public static final Color COLOR_WAIT = new Color(204,255,255);
	public static final Color COLOR_IN = Color.LIGHT_GRAY;
	public static final Font FONT = new Font("Courier New", Font.PLAIN, 13);



	private Service compHolder;
	private Service fieldFactory;
	private Service formHolder;
	private Service persist;
	private Service process;


	private JPanel panel;
	private JTextComponent console;
	private JTextField field_input;
	private JTextField field_root;
	
	private JButton button_killProcess;
	private JButton button_clearConsole;
	
	private PrintStream p_out;
	private PrintStream p_err;
	private PrintStream p_exit;
	private PrintStream p_bold;
	private PrintStream p_plain;
	
	private Process proc;
	private PrintStream proc_p;
	private S watch;
	
	
	

	public EntityImpl() throws Exception
	{
		compHolder = Outside.service(this,"*gus06.swing.textpane.holder.printstreamcomp");
		fieldFactory = Outside.service(this,"gus06.swing.textfield.factory.recallfield");
		formHolder = Outside.service(this,"*gus06.swing.panel.formpanel");
		persist = Outside.service(this,"gus06.swing.textcomp.persister.text");
		process = Outside.service(this,"gus06.process.holder1");
		
		
		
		field_root = new JTextField();
		persist.v(getClass().getName()+"_root",field_root);

		field_input = (JTextField) fieldFactory.i();
		field_input.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{sendCommand();}
		});
		
		button_killProcess = new JButton("kill process");
		button_killProcess.setEnabled(false);
		button_killProcess.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{killProcess();}
		});
		
		button_clearConsole = new JButton("clear console");
		button_clearConsole.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{clearConsole();}
		});
		
		console = (JTextComponent) compHolder.i();
		console.setMargin(new Insets(3,3,3,3));
		console.setEditable(false);
		console.setFont(FONT);
		
		
		
		formHolder.v("Command: ",field_input);
		formHolder.v("Root: ",field_root);
		
		JPanel p_bottom = new JPanel(new GridLayout(1,2,5,5));
		p_bottom.add(button_killProcess);
		p_bottom.add(button_clearConsole);
		
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) formHolder.i(),BorderLayout.NORTH);
		panel.add(new JScrollPane(console),BorderLayout.CENTER);
		panel.add(p_bottom,BorderLayout.SOUTH);
		
		
		p_out = (PrintStream) compHolder.r("blue");
		p_err = (PrintStream) compHolder.r("red");
		p_exit = (PrintStream) compHolder.r("green.darker");
		p_bold = (PrintStream) compHolder.r("bold black");
		p_plain = (PrintStream) compHolder.r("black");
		
		setBackground(COLOR_WAIT);
		
		process.v("p_out",p_out);
		process.v("p_err",p_err);
		process.v("p_exit",p_exit);
	}
	
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	
	private void setBackground(Color c)
	{
		field_input.setBackground(c);
		field_root.setBackground(c);
		console.setBackground(c);
	}
	
	
	private String command()
	{
		String text = field_input.getText();
		field_input.setText("");
		return text;
	}
	
	
	
	
	private void sendCommand()
	{
		try
		{
			String command = command();
			if(proc!=null) inputProcess(command);
			else startNewProcess(command);
		}
		catch(Exception e)
		{Outside.err(this,"sendCommand()",e);}
	}
	
	
	
	
	
	private synchronized void inputProcess(String command) throws Exception
	{
		p_plain.println(command);
		proc_p.println(command);
	}
	
	
	
	private synchronized void startNewProcess(String command) throws Exception
	{
		p_bold.println(command);
		
		try{proc = buildProc(command);}
		catch(Exception e)
		{
			e.printStackTrace(p_err);
			return;
		}
		
		proc_p = new PrintStream(proc.getOutputStream());
		button_killProcess.setEnabled(true);
		setBackground(COLOR_IN);
		
		if(watch!=null) watch.removeActionListener(this);
		watch = (S) process.t(proc);
		watch.addActionListener(this);
	}
	
	
	
	
	private Process buildProc(String command) throws Exception
	{
		String root = field_root.getText();
		if(root.equals("")) return Runtime.getRuntime().exec(command);
		File dir = new File(root);
		if(!dir.isDirectory()) throw new Exception("Invalid root dir: "+dir);
		return Runtime.getRuntime().exec(command,null,dir);
	}
	
	
	
	
	
	public void actionPerformed(ActionEvent e)
	{processOver();}
	
	
	
	
	private synchronized void processOver()
	{
		button_killProcess.setEnabled(false);
		proc_p.close();
		
		proc = null;
		proc_p = null;
		
		setBackground(COLOR_WAIT);
		Toolkit.getDefaultToolkit().beep();
	}
	
	
	
	
	private synchronized void killProcess()
	{proc.destroy();}
	
	private synchronized void clearConsole()
	{console.setText("");}
}
