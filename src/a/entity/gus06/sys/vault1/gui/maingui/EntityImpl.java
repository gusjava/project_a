package a.entity.gus06.sys.vault1.gui.maingui;

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

public class EntityImpl implements Entity, I, P, V {

	public String creationDate() {return "20200416";}


	private Service shiftPanel;
	private Service askPassword;
	private Service buildMap;
	private Service mapEditor;
	private Service backup;
	private Service changePwd;
	private Service storePwd;


	private JPanel panel;
	private JComponent editorComp;
	private JButton buttonConnect;
	private JButton buttonChange;

	private File root;
	private File vaultFile;
	
	private String persistKey;
	private Map map;
	

	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		askPassword = Outside.service(this,"gus06.security.askinfo.password1");
		buildMap = Outside.service(this,"gus06.sys.vault1.build.map");
		mapEditor = Outside.service(this,"*gus06.map.string.editor1");
		backup = Outside.service(this,"gus06.sys.vault1.backup");
		changePwd = Outside.service(this,"gus06.sys.vault1.change.pwd");
		storePwd = Outside.service(this,"gus06.sys.vault1.pwdstore");
		
		buttonConnect = new JButton("Connect to vault");
		buttonConnect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{connect();}
		});
		
		buttonChange = new JButton("Change password");
		buttonChange.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{changePwd();}
		});
		
		editorComp = (JComponent) mapEditor.i();
		editorComp.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		panel = new JPanel(new BorderLayout());
		panel.add(editorComp,BorderLayout.CENTER);
		panel.add(buttonChange,BorderLayout.SOUTH);
		
		shiftPanel.p(buttonConnect);
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	public void p(Object obj) throws Exception
	{
		root = (File) obj;
		vaultFile = new File(root,"vault");
		
		shiftPanel.p(buttonConnect);
		mapEditor.p(null);
		
		String pwd1 = getStoredPassword();
		if(pwd1!=null) connectWithPwd(pwd1);
	}


	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("persistKey")) {persistKey = (String) obj;return;}
		if(key.equals("connect")) {connectWithPwd((String) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private void connect()
	{
		try
		{
			String pwd = askPassword();
			if(pwd==null) return;
			
			connectWithPwd(pwd);
		}
		catch(Exception e)
		{Outside.err(this,"connect()",e);}
	}
	
	

	private void changePwd()
	{
		try
		{
			if(map==null) return;
			
			String pwd = askPassword();
			if(pwd==null) return;
			
			changePwd.p(new Object[]{vaultFile,map,pwd});
			connectWithPwd(pwd);
		}
		catch(Exception e)
		{Outside.err(this,"changePwd()",e);}
	}
	
	
	
	
	private String askPassword() throws Exception
	{
		String pwd = getStoredPassword();
		if(pwd!=null) return pwd;
		return (String) askPassword.g();
	}
	
	private String getStoredPassword() throws Exception
	{
		if(persistKey==null) return null;
		return (String) storePwd.r(persistKey);
	}
	
	
	
	
	
	private void connectWithPwd(String pwd) throws Exception
	{
		map = buildMap(pwd);
		if(map==null) return;
		
		backup.p(vaultFile);
		
		mapEditor.p(map);
		shiftPanel.p(panel);
		
		if(persistKey!=null)
		storePwd.v(persistKey,pwd);
	}
	
	
	private Map buildMap(String pwd) throws Exception
	{
		try{return (Map) buildMap.t(new Object[]{vaultFile,pwd});}
		catch(BadPaddingException e)
		{JOptionPane.showMessageDialog(null,"wrong password","failure",JOptionPane.ERROR_MESSAGE);}
		return null;
	}
}