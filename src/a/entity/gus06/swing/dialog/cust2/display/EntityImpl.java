package a.entity.gus06.swing.dialog.cust2.display;

import a.framework.*;
import javax.swing.JDialog;
import java.awt.Image;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20160831";}


	private Service imageProvider;

	public EntityImpl() throws Exception
	{imageProvider = Outside.service(this,"gus06.icon.provider.image");}
	
	
	public void v(String key, Object obj) throws Exception
	{perform(key,(JDialog) obj);}
	
	
	private void perform(String info, JDialog dialog) throws Exception
	{
		if(info==null || info.equals(""))
		{
			dialog.setTitle(" ");
			dialog.setIconImage(null);
		}
		else if(info.contains("#"))
		{
			String[] m = info.split("#",2);
			dialog.setTitle(m[1]);
			dialog.setIconImage(image(m[0]));
		}
		else
		{
			dialog.setTitle(info);
			dialog.setIconImage(null);
		}
	}
	
	private Image image(String id) throws Exception
	{return (Image) imageProvider.t(id);}
}
