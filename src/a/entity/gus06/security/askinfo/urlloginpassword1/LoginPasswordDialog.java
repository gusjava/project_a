package a.entity.gus06.security.askinfo.urlloginpassword1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class LoginPasswordDialog extends JDialog {


	private JTextField urlField;
	private JTextField loginField;
	private JPasswordField passwordField;
	
	
	public LoginPasswordDialog(JFrame parent)
	{
		super(parent,true);

		setTitle("Connection information");
		setResizable(false);

		setSize(300, 150);
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{dispose();}
		});
		
		urlField = new JTextField();
		urlField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{dispose();}
		});
		loginField = new JTextField();
		loginField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{dispose();}
		});
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{dispose();}
		});
		FormJPanel form = new FormJPanel();
		form.addLabel("url", urlField);
		form.addLabel("login", loginField);
		form.addLabel("password", passwordField);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(BorderLayout.CENTER, form);
	}
	
	
	
	
	
	
	public String[] getInfos()
	{
		String url = urlField.getText();
		String login = loginField.getText();
		String password = new String(passwordField.getPassword());
		
		urlField.setText("");
		loginField.setText("");
		passwordField.setText("");
		
		if(url.equals("")) return null;
		if(login.equals("")) return null;
		if(password.equals("")) return null;
		return new String[]{url,login,password};
	}
}
