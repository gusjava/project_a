package a.entity.gus06.security.askinfo.password1;

import a.framework.*;
import javax.swing.ImageIcon;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150613";}

	private PasswordDialog dialog;
	private ImageIcon icon;
	
	
	public EntityImpl() throws Exception
	{
		icon = (ImageIcon) Outside.resource(this,"icon#SECURITY_password");
		dialog = new PasswordDialog(null);
		if(icon!=null) dialog.setIconImage(icon.getImage());
	}

	public Object g() throws Exception
	{
		dialog.setVisible(true);
		return dialog.getPassword();
	}
}