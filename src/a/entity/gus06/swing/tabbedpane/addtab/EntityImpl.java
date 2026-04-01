package a.entity.gus06.swing.tabbedpane.addtab;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.Icon;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160831";}


	private Service iconProvider;
	
	public EntityImpl() throws Exception
	{
		iconProvider = Outside.service(this,"gus06.icon.provider");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		JTabbedPane tab = (JTabbedPane) o[0];
		JComponent comp = (JComponent) o[1];
		String display = (String) o[2];
		
		if(!display.contains("#"))
		{tab.addTab(display,comp);return;}
		
		String[] n = display.split("#",2);
		tab.addTab(n[1],icon(n[0]),comp);
	}
	
	
	private Icon icon(String id) throws Exception
	{return (Icon) iconProvider.t(id);}
}
