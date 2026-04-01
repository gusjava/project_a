package a.entity.gus06.swing.frame.cust2.display;

import a.framework.*;
import java.awt.Image;
import javax.swing.JFrame;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20140803";}


	private Service imageProvider;

	public EntityImpl() throws Exception
	{imageProvider = Outside.service(this,"gus06.icon.provider.image");}
	
	
	public void v(String key, Object obj) throws Exception
	{perform(key,(JFrame) obj);}
	
	
	private void perform(String info, JFrame frame) throws Exception
	{
		if(info==null || info.equals(""))
		{
			frame.setTitle(" ");
			frame.setIconImage(null);
		}
		else if(info.contains("#"))
		{
			String[] m = info.split("#",2);
			frame.setTitle(m[1]);
			frame.setIconImage(image(m[0]));
		}
		else
		{
			frame.setTitle(info);
			frame.setIconImage(null);
		}
	}
	
	private Image image(String id) throws Exception
	{return (Image) imageProvider.t(id);}
}
