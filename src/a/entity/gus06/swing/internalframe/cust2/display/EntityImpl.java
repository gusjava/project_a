package a.entity.gus06.swing.internalframe.cust2.display;

import a.framework.*;
import javax.swing.JInternalFrame;
import javax.swing.Icon;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20191122";}


	private Service iconProvider;

	public EntityImpl() throws Exception
	{iconProvider = Outside.service(this,"gus06.icon.provider");}
	
	
	public void v(String key, Object obj) throws Exception
	{perform(key,(JInternalFrame) obj);}
	
	
	private void perform(String info, JInternalFrame frame) throws Exception
	{
		if(info==null || info.equals(""))
		{
			frame.setTitle(" ");
			frame.setFrameIcon(null);
		}
		else if(info.contains("#"))
		{
			String[] m = info.split("#",2);
			frame.setTitle(m[1]);
			frame.setFrameIcon(icon(m[0]));
		}
		else
		{
			frame.setTitle(info);
			frame.setFrameIcon(null);
		}
	}
	
	private Icon icon(String id) throws Exception
	{return (Icon) iconProvider.t(id);}
}
