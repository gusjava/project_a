package a.entity.gus06.swing.label.cust2.icon.tooltip;

import javax.swing.Icon;
import javax.swing.JLabel;
import a.framework.*;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20150613";}



	private Service iconProvider;
	
	private Icon icon(String id) throws Exception
	{return (Icon) iconProvider.t(id);}
	
	public EntityImpl() throws Exception
	{iconProvider = Outside.service(this,"gus06.icon.provider");}



	public void v(String key, Object obj) throws Exception
	{repaintLabel((JLabel)obj,key);}
	
	
	
	
	private void repaintLabel(JLabel label, String info) throws Exception
	{
		if(info==null || info.equals(""))
		{
			label.setIcon(null);
			label.setToolTipText(null);
		}
		else if(info.contains("#"))
		{
			String[] m = info.split("#",2);
			label.setToolTipText(m[1]);
			label.setIcon(icon(m[0]));
		}
		else
		{
			label.setToolTipText(info);
			label.setIcon(null);
		}
	}


}
