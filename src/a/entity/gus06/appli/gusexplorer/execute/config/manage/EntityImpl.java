package a.entity.gus06.appli.gusexplorer.execute.config.manage;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20250828";}

	public static final String DISPLAY = "CONFIG_settings#Manage configs";
	public static final Dimension DIM = new Dimension(1200,700);
	

	private Service show;
	private Service build;
	
	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.frame.show2");
		build = Outside.service(this,"factory#gus06.appli.gusexplorer.config.gui1");
	}
	
	public void e() throws Exception
	{
		Object holder = build.g();
		Object comp = ((I) holder).i();
		
		show.p(new Object[]{comp,DIM,DISPLAY});
		
	}
}