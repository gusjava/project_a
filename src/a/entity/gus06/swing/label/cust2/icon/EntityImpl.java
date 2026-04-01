package a.entity.gus06.swing.label.cust2.icon;

import a.framework.*;
import javax.swing.Icon;
import javax.swing.JLabel;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20140803";}


	private Service iconProvider;

	public EntityImpl() throws Exception
	{iconProvider = Outside.service(this,"gus06.icon.provider");}
	
	
	public void v(String key, Object obj) throws Exception
	{perform(key,(JLabel) obj);}
	
	
	private void perform(String info, JLabel label) throws Exception
	{label.setIcon(icon(info));}
	
	
	private Icon icon(String id) throws Exception
	{return (Icon) iconProvider.t(id);}
}
