package a.entity.gus06.security.askinfo.loginpassword1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class LoginPasswordDialog extends JDialog {


	private JTextField loginField;
	private JPasswordField passwordField;
	
	
	public LoginPasswordDialog(JFrame parent)
	{
		super(parent,true);

		setTitle("Login information");
		setResizable(false);

		setSize(300, 120);
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
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
		form.addLabel("login", loginField);
		form.addLabel("password", passwordField);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(BorderLayout.CENTER, form);
	}
	
	
	
	
	
	
	public String[] getInfos()
	{
		String login = loginField.getText();
		String password = new String(passwordField.getPassword());
		
		loginField.setText("");
		passwordField.setText("");
		
		if(login.equals("")) return null;
		if(password.equals("")) return null;
		return new String[]{login,password};
	}
}
