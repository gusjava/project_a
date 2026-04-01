package a.entity.gus06.appli.labomail.gui.connect;

import a.framework.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.mail.Transport;
import javax.mail.Store;
import javax.swing.*;

public class EntityImpl extends S1 implements Entity, Runnable, ActionListener, I, R {

	public String creationDate() {return "20160607";}


	private Service holder;
	private Service persister;
	private Service form;
	
	private JTextField field_login;
	private JPasswordField field_pwd;
	

	private JPanel panel;
	private JComponent formComp;
	private JTextArea area;
	private JButton button;
	
	private Thread t;
	
	private Transport transport;
	private Store storeImap;
	private Store storePop3;
	


	public EntityImpl() throws Exception
	{
		holder = Outside.service(this,"gus06.mail.account.holder");
		persister = Outside.service(this,"gus06.app.persister1.manager.swing");
		form = Outside.service(this,"*gus06.swing.panel.formpanel");
		
		field_login = new JTextField();
		field_pwd = new JPasswordField();
		
		form.v("User",field_login);
		form.v("Password",field_pwd);
		
		persist("login",field_login);
		persist("pwd",field_pwd);
		
		formComp = (JComponent) form.i();
		
		button = new JButton("Connect");
		button.addActionListener(this);
		
		area = new JTextArea();
		area.setEditable(false);
		
		panel = new JPanel(new BorderLayout());
		panel.add(formComp,BorderLayout.NORTH);
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("storeImap")) return storeImap;
		if(key.equals("storePop3")) return storePop3;
		if(key.equals("transport")) return transport;
		if(key.equals("keys")) return new String[]{"storeImap","storePop3","transport"};
		
		throw new Exception("Unknown key: "+key);
	}




	private void persist(String key, JComponent comp) throws Exception
	{persister.v(getClass().getName()+"_"+key,comp);}


	public void actionPerformed(ActionEvent e)
	{connect();}
	
	
	private void connect()
	{
		if(t!=null && t.isAlive()) return;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	
	
	public void run()
	{
		button.setForeground(Color.ORANGE);
		button.setText("Connecting...");
		
		area.setText("");
		
		perform();
		
		button.setForeground(Color.BLACK);
		button.setText("Connect");
	}
	
	
	
	
	
	private void perform()
	{
		if(!prepareHolder()) return;
		
		boolean storeImapOk = initStoreImap();
		boolean storePop3Ok = initStorePop3();
		boolean transportOk = initTransport();
		
		if(storeImapOk || storePop3Ok || transportOk) connected();
	}
	
	
	
	
	private boolean prepareHolder()
	{
		try
		{
			
			String login = field_login.getText();
			String password = new String(field_pwd.getPassword());
			
			storeImap = null;
			storePop3 = null;
			transport = null;
			
			holder.e();
			holder.v("login",login);
			holder.v("password",password);
			
			return true;
		}
		catch(Exception e)
		{
			Outside.err(this,"prepareHolder()",e);
			area.append(e+"\n");
			return false;
		}
	}
	
	
	
	
	
	private boolean initStoreImap()
	{
		try
		{
			storeImap = (Store) holder.r("storeImap");
			area.append("Store Imap initialized\n\n");
			return true;
		}
		catch(Exception e)
		{
			Outside.err(this,"initStoreImap()",e);
			area.append("Store failed:\n"+e+"\n");
			return false;
		}
	}
	
	
	private boolean initStorePop3()
	{
		try
		{
			storePop3 = (Store) holder.r("storePop3");
			area.append("Store Pop3 initialized\n\n");
			return true;
		}
		catch(Exception e)
		{
			Outside.err(this,"initStorePop3()",e);
			area.append("Store failed:\n"+e+"\n");
			return false;
		}
	}
	
	
	
	private boolean initTransport()
	{
		try
		{
			transport = (Transport) holder.r("transport");
			area.append("Transport initialized\n\n");
			return true;
		}
		catch(Exception e)
		{
			Outside.err(this,"initTransport()",e);
			area.append("Transport failed:\n"+e+"\n");
			return false;
		}
	}


	private void connected()
	{send(this,"connected()");}
}