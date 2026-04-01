package a.entity.gus06.swing.button.cust2.icon.tooltip;

import javax.swing.Icon;
import javax.swing.AbstractButton;
import a.framework.*;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20220526";}



	private Service iconProvider;
	
	private Icon icon(String id) throws Exception
	{return (Icon) iconProvider.t(id);}
	
	public EntityImpl() throws Exception
	{iconProvider = Outside.service(this,"gus06.icon.provider");}



	public void v(String key, Object obj) throws Exception
	{repaintLabel((AbstractButton)obj,key);}
	
	
	
	
	private void repaintLabel(AbstractButton button, String info) throws Exception
	{
		if(info==null || info.equals(""))
		{
			button.setIcon(null);
			button.setToolTipText(null);
		}
		else if(info.contains("#"))
		{
			String[] m = info.split("#",2);
			button.setToolTipText(m[1]);
			button.setIcon(icon(m[0]));
		}
		else
		{
			button.setToolTipText(info);
			button.setIcon(null);
		}
	}


}