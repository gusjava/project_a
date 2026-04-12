package a.entity.gus06.sys.desktop1.gui.maingui;

import a.framework.*;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Map;
import java.io.File;
import javax.swing.JToolBar;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20191120";}

	private Service buildPane;
	private Service buildMain;
	private Service custPane;
	private Service custBar;
	private Service buildBar;
	private Service loadAll;
	
	private JPanel panel;
	private JDesktopPane pane;
	private JToolBar bar;

	private File root;
	private Map main;
	

	public EntityImpl() throws Exception
	{
		buildPane = Outside.service(this,"gus06.swing.desktoppane.buildpane1");
		buildMain = Outside.service(this,"gus06.sys.desktop1.data.main.build");
		custPane = Outside.service(this,"gus06.sys.desktop1.gui.pane.cust");
		custBar = Outside.service(this,"gus06.sys.desktop1.gui.bar.cust");
		buildBar = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		loadAll = Outside.service(this,"gus06.sys.desktop1.item.manager.load");
		
		pane = (JDesktopPane) buildPane.i();
		bar = (JToolBar) buildBar.i();
		
		panel = new JPanel(new BorderLayout());
		panel.add(bar,BorderLayout.NORTH);
		panel.add(pane,BorderLayout.CENTER);
	}
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		root = (File) obj;
		main = (Map) buildMain.t(new Object[]{root,panel,pane,bar});
		
		custPane.p(main);
		custBar.p(main);
		loadAll.p(main);
	}
}
