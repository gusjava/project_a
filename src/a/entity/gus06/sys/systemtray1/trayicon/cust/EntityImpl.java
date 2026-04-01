package a.entity.gus06.sys.systemtray1.trayicon.cust;

import a.framework.*;
import java.awt.TrayIcon;
import java.awt.SystemTray;
import javax.swing.JPopupMenu;
import javax.swing.Action;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180309";}


	private Service getActionExit;
	private Service buildPopupMenu;
	
	private Action actionExit;
	
	public EntityImpl() throws Exception
	{
		getActionExit = Outside.service(this,"gus06.app.action.exit.en");
		buildPopupMenu = Outside.service(this,"gus06.awt.trayicon.build.popupmenu1");
		actionExit = (Action) getActionExit.g();
	}
	
	
	public void p(Object obj) throws Exception
	{
		TrayIcon appTray = (TrayIcon) obj;
		JPopupMenu menu = (JPopupMenu) buildPopupMenu.t(appTray);
		menu.add(actionExit);
	}
}
