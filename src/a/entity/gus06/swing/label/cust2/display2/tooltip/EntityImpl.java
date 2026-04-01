package a.entity.gus06.swing.label.cust2.display2.tooltip;

import javax.swing.Icon;
import javax.swing.JLabel;
import a.framework.*;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20250727";}


	private Service iconProvider;
	
	public EntityImpl() throws Exception
	{iconProvider = Outside.service(this,"gus06.icon.provider");}


	public void v(String key, Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		perform(key,(JLabel) o[0], (R) o[1]);
	}
	
	
	private void perform(String info, JLabel label, R ip) throws Exception
	{
		if(info==null || info.equals(""))
		{
			label.setText(" ");
			label.setIcon(null);
			label.setToolTipText(null);
		}
		else if(info.contains("#"))
		{
			String[] m = info.split("#",2);
			label.setText(m[1]);
			label.setToolTipText(m[1]);
			label.setIcon(icon(ip, m[0]));
		}
		else
		{
			label.setText(info);
			label.setToolTipText(info);
			label.setIcon(null);
		}
	}
	
	private Icon icon(R ip, String id) throws Exception
	{
		if(ip!=null)
		{
			Icon icon = (Icon) ip.r(id);
			if(icon!=null) return icon;
		}
		return (Icon) iconProvider.t(id);
	}
}