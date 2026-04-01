package a.entity.gus06.appli.gusexplorer.execute.tools.monitor.show;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20220918";}
	
	public static final Dimension DIM = new Dimension(835,500);
	public static final String DISPLAY = "UTIL_monitor#Monitor";

	private Service viewer;
	private Service show;

	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.sys.screen1.viewer.printscreen2.withmouse");
		show = Outside.service(this,"gus06.swing.frame.show2");
	}
	
	public void e() throws Exception
	{
		show.p(new Object[]{viewer,DIM,DISPLAY});
	}
}
