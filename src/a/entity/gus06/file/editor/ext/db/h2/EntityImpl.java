package a.entity.gus06.file.editor.ext.db.h2;

import java.awt.BorderLayout;
import java.io.File;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250723";}


	private Service shiftPanel;
	private Service viewer;
	private Service askLoginPwd;
	private Service buildCx;
	
	private JButton button;
	private File file;
	

	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		viewer = Outside.service(this,"*gus06.y.h2viewer1.maingui");
		askLoginPwd = Outside.service(this,"gus06.security.askinfo.loginpassword1");
		buildCx = Outside.service(this,"gus06.y.api2.h2.cx.build");
		
		button = new JButton("Connect");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {connect();}
		});
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null || !file.isFile() || file.length()==0) resetGui();
		else updateGui();
	}
	
	
	private void resetGui() throws Exception
	{
		button.setEnabled(false);
		viewer.p(null);
		shiftPanel.p(null);
	}
	
	
	private void updateGui() throws Exception
	{
		G getCx = defaultCxBuilder();
		if(getCx!=null)
		{
			button.setEnabled(false);
			viewer.p(getCx);
			shiftPanel.p(viewer);
		}
		else
		{
			button.setEnabled(true);
			viewer.p(null);
			shiftPanel.p(button);
		}
	}
	
	
	private void connect()
	{
		try
		{
			String[] infos = (String[]) askLoginPwd.g();
			if(infos==null) return;
			
			String login = infos[0];
			String pwd = infos[1];
			
			G getCx = securedCxBuilder(login, pwd);
			if(getCx!=null)
			{
				button.setEnabled(false);
				viewer.p(getCx);
				shiftPanel.p(viewer);
			}
			else
			{
				String message = "Failed to connect to database";
				String title = "Connection error";
				JOptionPane.showMessageDialog(null,message,title, JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception e)
		{Outside.err(this,"connect()",e);}
	}
	
	
	
	
	private G defaultCxBuilder()
	{
		try
		{
			G getCx = new CxBuilder(file, "sa", "");
			Connection cx = (Connection) getCx.g();
			cx.close();
			return getCx;
		}
		catch(Exception e){}
		return null;
	}
	
	
	
	private G securedCxBuilder(String login, String pwd)
	{
		try
		{
			
			G getCx = new CxBuilder(file, login, pwd);
			Connection cx = (Connection) getCx.g();
			cx.close();
			return getCx;
		}
		catch(Exception e){}
		return null;
	}
	
	
	private class CxBuilder implements G, R
	{
		private File file;
		private String login;
		private String pwd;
		
		public CxBuilder(File file, String login, String pwd)
		{
			this.file = file;
			this.login = login;
			this.pwd = pwd;
		}
		
		public Object g() throws Exception
		{return buildCx.t(new Object[]{file,login,pwd});	}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("userName")) return login;
			if(key.equals("keys")) return new String[]{"userName"};
			throw new Exception("Unknown key: "+key);
		}
	}
}