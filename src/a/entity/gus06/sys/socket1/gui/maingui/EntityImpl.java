package a.entity.gus06.sys.socket1.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.text.JTextComponent;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Map;
import javax.swing.JTextField;

public class EntityImpl implements Entity, ActionListener, I, P {

	public String creationDate() {return "20221110";}
	
	public static final String KEY_ROOT = "path.root";
	public static final String KEY_LOCAL_PORT = "local.port";
	public static final String KEY_REMOTE_PORT = "remote.port";
	public static final String KEY_REMOTE_IP = "remote.ip";
	
	public static final Font FONT = new Font("Courier New", Font.PLAIN, 12);


	private Service executeOnCtrlV;
	private Service compHolder;
	private Service fromClipboard;
	private Service engine;
	private Service dnd;

	private JPanel panel;
	private JTextComponent console;
	private JTextField field;
	
	

	public EntityImpl() throws Exception
	{
		executeOnCtrlV = Outside.service(this,"gus.x.swing.comp.cust3.execute.ctrl_v");
		compHolder = Outside.service(this,"*gus06.swing.textpane.holder.printstreamcomp");
		fromClipboard = Outside.service(this,"gus06.sys.clipboard1.g.listfiles");
		engine = Outside.service(this,"*gus.y.serversocket1.engine");
		dnd = Outside.service(this,"gus06.awt.dnd");
		
		console = (JTextComponent) compHolder.i();
		
		field = new JTextField();
		field.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(console), BorderLayout.CENTER);
		panel.add(field, BorderLayout.SOUTH);
		
		
		E eTransfer = (E) this::sendDataFromClipboard;
		executeOnCtrlV.p(new Object[]{console, eTransfer});
		
		P pDnd = (P) this::sendData;
		dnd.p(new Object[]{console, pDnd, null});
		
		console.setMargin(new Insets(3,3,3,3));
		console.setBackground(Color.BLACK);
		console.setEditable(false);
		console.setFont(FONT);
		
		field.setMargin(new Insets(3,3,3,3));
		field.setBackground(Color.BLACK);
		field.setForeground(Color.WHITE);
		field.setCaretColor(Color.WHITE);
		field.setFont(FONT);
		
		PrintStream out = (PrintStream) compHolder.r("cyan");
		PrintStream in = (PrintStream) compHolder.r("green");
		
		engine.v("out",out);
		engine.v("in",in);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{engine.p(obj);}
	


	public void actionPerformed(ActionEvent e)
	{sendCommand();}
	
	
	// SEND DATA
	
	private void sendDataFromClipboard()
	{
		try
		{
			sendData(fromClipboard.g());
		}
		catch(Exception e)
		{Outside.err(this,"sendDataFromClipboard()",e);}
	}
	
	private void sendData(Object data)
	{
		try
		{
			engine.v("sendData",data);
		}
		catch(Exception e)
		{Outside.err(this,"sendData(Object)",e);}
	}
	
	// SEND COMMAND
	
	private void sendCommand()
	{
		try
		{
			String command = field.getText();
			if(command.equals("")) return;
			
			boolean sent = engine.f(command);
			if(sent) field.setText("");
		}
		catch(Exception e)
		{Outside.err(this,"sendCommand()",e);}
	}
}