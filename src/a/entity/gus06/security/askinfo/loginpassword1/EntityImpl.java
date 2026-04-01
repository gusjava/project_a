package a.entity.gus06.security.askinfo.loginpassword1;

import a.framework.*;
import javax.swing.ImageIcon;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160608";}

	private LoginPasswordDialog dialog;
	private ImageIcon icon;
	
	public EntityImpl() throws Exception
	{
		icon = (ImageIcon) Outside.resource(this,"icon#SECURITY_password");
		dialog = new LoginPasswordDialog(null);
		if(icon!=null) dialog.setIconImage(icon.getImage());
	}

	public Object g() throws Exception
	{
		dialog.setVisible(true);
		return dialog.getInfos();
	}
}