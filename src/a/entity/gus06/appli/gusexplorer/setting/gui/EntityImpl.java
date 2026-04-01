package a.entity.gus06.appli.gusexplorer.setting.gui;

import a.framework.*;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20170513";}

	private Service tab;
	private Service guiKS;
	private Service guiAdv;
	private Service setBorder;
	
	private JComponent comp;
	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		guiKS = Outside.service(this,"*gus06.sys.keystroke1.gui1");
		guiAdv = Outside.service(this,"*gus06.app.setting.prop");
		
		setBorder = Outside.service(this,"gus06.swing.comp.cust.border.empty10");
		
		tab.v("UTIL_keyboard#Keystrokes",guiKS.i());
		tab.v("UTIL_warning#Advanced",guiAdv.i());
		
		comp = (JComponent) tab.i();
		setBorder.p(comp);
	}
	
	
	public Object i() throws Exception
	{return comp;}
}
