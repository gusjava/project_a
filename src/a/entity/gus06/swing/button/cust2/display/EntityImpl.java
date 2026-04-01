package a.entity.gus06.swing.button.cust2.display;

import a.framework.*;
import javax.swing.Icon;
import javax.swing.AbstractButton;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20140908";}


	private Service iconProvider;

	public EntityImpl() throws Exception
	{iconProvider = Outside.service(this,"gus06.icon.provider");}
	
	
	public void v(String key, Object obj) throws Exception
	{perform(key,(AbstractButton) obj);}
	
	
	private void perform(String info, AbstractButton button) throws Exception
	{
		if(info==null || info.equals(""))
		{
			button.setText(" ");
			button.setIcon(null);
		}
		else if(info.contains("#"))
		{
			String[] m = info.split("#",2);
			button.setText(m[1]);
			button.setIcon(icon(m[0]));
		}
		else
		{
			button.setText(info);
			button.setIcon(null);
		}
	}
	
	private Icon icon(String id) throws Exception
	{return (Icon) iconProvider.t(id);}
}
