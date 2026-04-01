package a.entity.gus06.appli.gusexplorer.menu.tools.screen;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.Action;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20191020";}

	
	
	private Service captureToClipboard;
	private Service captureToTempDir;
	private Service captureShowOnTop;
	
	private Service capture2ToClipboard;
	private Service capture2ToTempDir;
	private Service capture2ShowOnTop;
	
	private JMenu menu;


	public EntityImpl() throws Exception
	{
		captureToClipboard = Outside.service(this,"gus06.appli.gusexplorer.action.tools.screen.capture.toclipboard");
		captureToTempDir = Outside.service(this,"gus06.appli.gusexplorer.action.tools.screen.capture.totempdir");
		captureShowOnTop = Outside.service(this,"gus06.appli.gusexplorer.action.tools.screen.capture.showontop");
		
		capture2ToClipboard = Outside.service(this,"gus06.appli.gusexplorer.action.tools.screen.capture2.toclipboard");
		capture2ToTempDir = Outside.service(this,"gus06.appli.gusexplorer.action.tools.screen.capture2.totempdir");
		capture2ShowOnTop = Outside.service(this,"gus06.appli.gusexplorer.action.tools.screen.capture2.showontop");
	
		menu = new JMenu("Screen");
		
		add(captureToClipboard);
		add(captureToTempDir);
		add(captureShowOnTop);
		
		menu.addSeparator();
		
		add(capture2ToClipboard);
		add(capture2ToTempDir);
		add(capture2ShowOnTop);
	}
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}