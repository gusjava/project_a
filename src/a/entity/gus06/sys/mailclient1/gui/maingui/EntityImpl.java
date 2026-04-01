package a.entity.gus06.sys.mailclient1.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.util.Map;

public class EntityImpl implements Entity, ActionListener, I, V, Runnable {

	public String creationDate() {return "20201112";}


	private Service holder;
	private Service storeViewer;
	private Service renderLabel;

	private JPanel panel;
	private JButton button;
	private JLabel label;
	
	private Thread t;
	
	private File root;
	private Object loginPwd;
	private String type;
	

	public EntityImpl() throws Exception
	{
		holder = Outside.service(this,"*gus06.mail.account.holder");
		storeViewer = Outside.service(this,"*gus06.sys.mailclient1.gui.tab1");
		renderLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		
		label = new JLabel(" ");
		label.setBorder(BorderFactory.createRaisedBevelBorder());
		
		button = new JButton("Connect");
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add((JComponent) storeViewer.i(),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("root")) {setRoot((File) obj);return;}
		if(key.equals("loginPwd")) {setLoginPwd(obj);return;}
		if(key.equals("type")) {type = (String) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void setLoginPwd(Object loginPwd)
	{
		this.loginPwd = loginPwd;
		renderLabel("UTIL_mail");
	}
	
	private void setRoot(File root) throws Exception
	{
		this.root = root;
		storeViewer.v("root", root);
	}


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
		
		if(perform())
		{
			button.setForeground(Color.BLUE);
			button.setText("Connected");
			renderLabel("UTIL_mail_connected");
		}
		else
		{
			button.setForeground(Color.RED);
			button.setText("Connection failed");
			renderLabel("UTIL_mail_error");
		}
	}
	
	
	private boolean perform()
	{
		try
		{
			storeViewer.p(null);
			initLoginAndPassword();
			
			holder.e();
			holder.v("login",findLogin());
			holder.v("password",findPassword());
			holder.v("type",type);
			
			storeViewer.p(holder);
			return true;
		}
		catch(Exception e)
		{
			Outside.err(this,"perform()",e);
			return false;
		}
	}
	
	
	
	private void renderLabel(String iconId)
	{
		try
		{
			String login = findLogin();
			String display = login!=null ? iconId+"#"+login : " ";
			renderLabel.v(display,label);
		}
		catch(Exception e)
		{Outside.err(this,"renderLabel(String)",e);}
	}
	
	
	
	private void initLoginAndPassword() throws Exception
	{
		if(loginPwd==null) return;
		((E) loginPwd).e();
	}
	
	private String findLogin() throws Exception
	{
		if(loginPwd==null) return null;
		return (String) ((R) loginPwd).r("login");
	}
	
	private String findPassword() throws Exception
	{
		if(loginPwd==null) return null;
		return (String) ((R) loginPwd).r("password");
	}
}
