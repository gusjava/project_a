package a.entity.gus06.sys.ssh1.gui1;

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
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.util.Map;
import java.io.InputStream;
import java.util.HashMap;
import java.io.OutputStream;


public class EntityImpl implements Entity, I, V, R, Runnable {

	public String creationDate() {return "20180329";}
	
	public static final Font FONT = new Font("Courier New", Font.PLAIN, 13);


	private Service fieldFactory;
	private Service buildOS;
	private Service buildIS;
	private Service buildSSH;


	private JPanel panel;
	private JTextPane console;
	private JTextField field;
	private JButton button;
	
	private Thread t;
	private InputStream is;
	private OutputStream os;
	
	private T cmdBuilder;
	private File root;
	private Map data;
	
	

	public EntityImpl() throws Exception
	{
		fieldFactory = Outside.service(this,"gus06.swing.textfield.factory.recallfield");
		buildOS = Outside.service(this,"gus06.io.outputstream.textpane1.shell");
		buildIS = Outside.service(this,"gus06.io.generate.pipedinput.withp.utf8");
		buildSSH = Outside.service(this,"gus06.sys.ssh1.build");
		
		
		button = new JButton("Connect");
		
		field = (JTextField) fieldFactory.i();
		field.setBackground(Color.BLACK);
		field.setForeground(Color.WHITE);
		field.setCaretColor(Color.WHITE);
		field.setMargin(new Insets(3,3,3,3));
		field.setFont(FONT);
		
		console = new JTextPane();
		console.setBackground(Color.BLACK);
		console.setForeground(Color.WHITE);
		console.setMargin(new Insets(3,3,3,3));
		console.setEditable(false);
		console.setFont(FONT);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(new JScrollPane(console),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
		
		is = (InputStream) buildIS.g();
		os = (OutputStream) buildOS.t(console);
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{connect();}
		});
		field.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{send();}
		});
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	
	private void send()
	{
		try
		{
			String cmd = field.getText();
			field.setText("");
		
			if(cmd.equals("clear"))
			{
				console.setText("");
				return;
			}
			
			if(cmdBuilder!=null) cmd = (String) cmdBuilder.t(cmd);
			((P) is).p(cmd);
		}
		catch(Exception e)
		{Outside.err(this,"send()",e);}
	}
	
	
	
	
	
	private void connect()
	{
		if(t!=null) return;
		button.setEnabled(false);
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	
	public void run()
	{
		try
		{
			Map m = new HashMap(data);
			m.put("input",is);
			m.put("output",os);
			m.put("prompt_yesno",new F(){
				public boolean f(Object obj) throws Exception
				{return true;}
			});
			
			File authFile = new File(root,"auth.pem");
			if(authFile.isFile()) m.put("auth",authFile);
			
			buildSSH.p(m);
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("cmdBuilder")) {cmdBuilder = (T) obj;return;}
		if(key.equals("data")) {data = (Map) obj;return;}
		if(key.equals("root")) {root = (File) obj;return;}
		
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
