package a.entity.gus06.sys.systemtray1.init;

import a.framework.*;
import java.io.PrintStream;
import java.awt.TrayIcon;
import java.awt.SystemTray;
import javax.swing.JFrame;

public class EntityImpl implements Entity {

	public String creationDate() {return "20160422";}
	
	public static final String KEY_SHOW_TRAYICON = "app.show.trayicon";


	private Service buildAppTray;
	private Service frameToggle;
	private Service custAppTray;
	private Service propBoolDF;
	
	private TrayIcon appTray;
	private JFrame mainFrame;
	private PrintStream out;
	
	

	public EntityImpl() throws Exception
	{
		buildAppTray = Outside.service(this,"gus06.sys.systemtray1.trayicon.app");
		custAppTray = Outside.service(this,"gus06.sys.systemtray1.trayicon.cust");
		frameToggle = Outside.service(this,"gus06.sys.systemtray1.frametoggle");
		propBoolDF = Outside.service(this,"propbool_df");
		
		mainFrame = (JFrame) Outside.resource(this,"mainframe");
		out = (PrintStream) Outside.resource(this,"sysout");
		
		if(propBoolDF.f(KEY_SHOW_TRAYICON)) initSystemTray();
	}
	
	
	private void initSystemTray() throws Exception
	{
		if(!SystemTray.isSupported())
		{
			out.println("SystemTray is not available: entity disabled");
			return;
		}
		
		appTray = (TrayIcon) buildAppTray.g();
		custAppTray.p(appTray);
		
		SystemTray.getSystemTray().add(appTray);
		frameToggle.p(new Object[]{appTray,mainFrame});
	}
}
