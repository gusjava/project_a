package a.entity.gus06.jdbc.gui.connect1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class EntityImpl implements Entity, I, G {

	public String creationDate() {return "20150621";}


	private Service form;
	private Service persister;
	
	private JTextField field_url;
	private JTextField field_user;
	private JPasswordField field_pwd;

	

	public EntityImpl() throws Exception
	{
		form = Outside.service(this,"*gus06.swing.panel.formpanel");
		persister = Outside.service(this,"gus06.app.persister1.manager.swing");
		
		field_url = new JTextField();
		field_user = new JTextField();
		field_pwd = new JPasswordField();
		
		form.v("Url",field_url);
		form.v("User",field_user);
		form.v("Password",field_pwd);
		
		persist("url",field_url);
		persist("user",field_user);
		persist("pwd",field_pwd);
	}
	
	
	public Object i() throws Exception
	{return form.i();}
	
	
	
	public Object g() throws Exception
	{
		String url = field_url.getText();
		String user = field_user.getText();
		String pwd = new String(field_pwd.getPassword());
		
		return new String[]{url,user,pwd};
	}




	private void persist(String key, JComponent comp) throws Exception
	{persister.v(getClass().getName()+"_"+key,comp);}
}
