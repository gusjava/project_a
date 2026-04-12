package a.entity.gus06.security.askinfo.password1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PasswordDialog extends JDialog {

	private JPasswordField passwordField;

	public PasswordDialog(JFrame parent)
	{
		super(parent,true);

		setTitle("Password");
		setResizable(false);

		setSize(300, 80);
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{dispose();}
		});
		
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{dispose();}
		});
		FormJPanel form = new FormJPanel();
		form.addLabel("password", passwordField);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(BorderLayout.CENTER, form);
	}
	
	
	
	
	
	
	public String getPassword()
	{
		String password = new String(passwordField.getPassword());
		passwordField.setText("");
		return password.equals("")?null:password;
	}
}
