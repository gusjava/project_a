package a.entity.gus06.file.editor.ext.properties_vault;

import a.framework.*;
import java.io.File;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.swing.JComponent;
import javax.swing.BorderFactory;
import java.awt.GridLayout;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20231028";}

	public static final String KEY_CRYPTED = "CRYPTED";

	private Service readProp;
	private Service writeProp;
	private Service shiftPanel;
	private Service mapEditor;
	private Service askPwd;
	private Service askPwdLogin;
	private Service decrypt;
	private Service encrypt;
	private Service storePwd;


	private JPanel panel;
	private JComponent editorComp;
	
	private JButton buttonInit;
	private JButton buttonConnect;
	private JButton buttonReset;
	private JButton buttonSave;
	private JButton buttonReload;

	private File file;
	
	private String login;
	private String pwd;
	private Map dataMap;
	

	public EntityImpl() throws Exception
	{
		readProp = Outside.service(this,"gus.x.file.prop.read");
		writeProp = Outside.service(this,"gus06.file.write.properties");
		shiftPanel = Outside.service(this,"*gus06.swing.panel.shiftpanel");
		mapEditor = Outside.service(this,"*gus06.map.string.editor1");
		askPwd = Outside.service(this,"gus06.security.askinfo.password1");
		askPwdLogin = Outside.service(this,"gus06.security.askinfo.loginpassword1");
		decrypt = Outside.service(this,"gus06.crypto.pbe.object.decrypt.base64");
		encrypt = Outside.service(this,"gus06.crypto.pbe.object.encrypt.base64");
		storePwd = Outside.service(this,"gus06.sys.vault1.pwdstore");
		
		buttonInit = new JButton("Init vault");
		buttonInit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{init();}
		});
		
		buttonConnect = new JButton("Connect to vault");
		buttonConnect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{connect();}
		});
		
		buttonReset = new JButton("Reset vault");
		buttonReset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{reset();}
		});
		
		buttonSave = new JButton("Save data");
		buttonSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{save();}
		});
		
		buttonReload = new JButton("Reload data");
		buttonReload.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{reload();}
		});
		
		editorComp = (JComponent) mapEditor.i();
		editorComp.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		JPanel panelButtons = new JPanel(new GridLayout(1,3));
		panelButtons.add(buttonReset);
		panelButtons.add(buttonSave);
		panelButtons.add(buttonReload);
		
		panel = new JPanel(new BorderLayout());
		panel.add(editorComp, BorderLayout.CENTER);
		panel.add(panelButtons, BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		
		dataMap = null;
		login = null;
		pwd = null;
		shiftPanel.p(null);
		mapEditor.p(null);
		
		if(file==null || !file.isFile()) return;
		
		Map map = (Map) readProp.t(file);
		if(!map.containsKey(KEY_CRYPTED))
		{
			shiftPanel.p(buttonInit);
			return;
		}
		login = (String) map.get(KEY_CRYPTED);
		map.remove(KEY_CRYPTED);
		
		pwd = (String) storePwd.r(login);
		if(pwd==null)
		{
			shiftPanel.p(buttonConnect);
			return;
		}
		
		dataMap = decrypt(map, pwd);
		if(dataMap==null)
		{
			shiftPanel.p(buttonConnect);
			return;
		}
		
		mapEditor.p(dataMap);
		shiftPanel.p(panel);
	}
	
	
	
	
	private void init()
	{
		try
		{
			if(file==null) return;
			
			Map map = (Map) readProp.t(file);
			if(map.containsKey(KEY_CRYPTED))
			{
				showErr("Failure", "Vault already initialized");
				return;
			}
			
			String[] infos = (String[]) askPwdLogin.g();
			if(infos==null) return;
				
			String login1 = (String) infos[0];
			String pwd1 = (String) infos[1];
			
			String pwd0 = (String) storePwd.r(login1);
			if(pwd0!=null && !pwd0.equals(pwd1))
			{
				showErr("Failure", "Invalid password for login: "+login1);
				return;
			}
			
			Map mapEnc = encrypt(map, pwd1);
			mapEnc.put(KEY_CRYPTED, login1);
			writeProp.p(new Object[]{file, mapEnc});
			
			login = login1;
			pwd = pwd1;
			dataMap = map;
		
			mapEditor.p(dataMap);
			shiftPanel.p(panel);
			storePwd.v(login, pwd);
		}
		catch(Exception e)
		{
			Outside.err(this,"init()",e);
			showErr("Failure", "An error occurred while initializing");
		}
	}
	
	
	
	
	private void connect()
	{
		try
		{
			if(file==null) return;
			if(login==null) return;
			
			Map mapEnc = (Map) readProp.t(file);
			if(!mapEnc.containsKey(KEY_CRYPTED))
			{
				showErr("Failure", "Vault not initialized yet");
				shiftPanel.p(buttonInit);
				return;
			}
			String login1 = (String) mapEnc.get(KEY_CRYPTED);
			mapEnc.remove(KEY_CRYPTED);
			
			if(!login1.equals(login))
			{
				showErr("Failure", "Login has been modified inside file");
				return;
			}
			
			String pwd1 = askPwd();
			if(pwd1==null) return;
			
			Map mapDecr = decrypt(mapEnc, pwd1);
			if(mapDecr!=null)
			{
				pwd = pwd1;
				dataMap = mapDecr;
				
				mapEditor.p(dataMap);
				shiftPanel.p(panel);
				storePwd.v(login, pwd1);
			}
		}
		catch(Exception e)
		{
			Outside.err(this,"connect()",e);
			showErr("Failure", "An error occurred while connecting");
		}
	}
	
	
	

	private void reset()
	{
		try
		{
			if(dataMap==null) return;
			if(login==null) return;
			if(pwd==null) return;
			
			String pwd1 = askPwd();
			if(pwd1==null) return;
			
			Map mapEnc = encrypt(dataMap, pwd1);
			mapEnc.put(KEY_CRYPTED, login);
			writeProp.p(new Object[]{file, mapEnc});
			
			pwd = pwd1;
			storePwd.v(login, pwd1);
		}
		catch(Exception e)
		{
			Outside.err(this,"reset()",e);
			showErr("Failure", "An error occurred while resetting");
		}
	}
	
	
	private void save()
	{
		try
		{
			if(dataMap==null) return;
			if(login==null) return;
			if(pwd==null) return;
			
			Map mapEnc = encrypt(dataMap, pwd);
			mapEnc.put(KEY_CRYPTED, login);
			writeProp.p(new Object[]{file, mapEnc});
		}
		catch(Exception e)
		{
			Outside.err(this,"save()",e);
			showErr("Failure", "An error occurred while saving");
		}
	}
	
	
	private void reload()
	{
		try
		{
			if(file==null) return;
			if(login==null) return;
			if(pwd==null) return;
			
			Map mapEnc = (Map) readProp.t(file);
			if(!mapEnc.containsKey(KEY_CRYPTED))
			{
				showErr("Failure", "Vault not initialized yet");
				shiftPanel.p(buttonInit);
				return;
			}
			String login1 = (String) mapEnc.get(KEY_CRYPTED);
			mapEnc.remove(KEY_CRYPTED);
			
			if(!login1.equals(login))
			{
				showErr("Failure", "Login has been modified inside file");
				return;
			}
			
			Map mapDecr = decrypt(mapEnc, pwd);
			if(mapDecr!=null)
			{
				dataMap = mapDecr;
				mapEditor.p(dataMap);
			}
		}
		catch(Exception e)
		{
			Outside.err(this,"reload()",e);
			showErr("Failure", "An error occurred while reloading");
		}
	}
	
	
	
	
	
	
	private String askPwd() throws Exception
	{
		String pwd = (String) storePwd.r(login);
		if(pwd!=null) return pwd;
		return (String) askPwd.g();
	}
	
	private Map decrypt(Map map, String pwd) throws Exception
	{
		try
		{
			T decrypter = (T) decrypt.t(pwd);
			return (Map) decrypter.t(map);
		}
		catch(BadPaddingException e)
		{showErr("Failure", "Wrong password");}
		return null;
	}
	
	private Map encrypt(Map map, String pwd) throws Exception
	{
		T encrypter = (T) encrypt.t(pwd);
		return (Map) encrypter.t(map);
	}
	
	private void showErr(String title, String msg)
	{JOptionPane.showMessageDialog(null,msg,title,JOptionPane.ERROR_MESSAGE);}
}