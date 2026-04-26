package a.entity.gus06.app.execute.debug;

import a.framework.*;
import java.awt.Dimension;

public class EntityImpl extends S1 implements Entity, E {

	public String creationDate() {return "20250828";}


	public static final String DISPLAY = "UTIL_debug#Debug tools";
	public static final Dimension DIM = new Dimension(1200,700);

	private Service show;
	private Service build;
	


	public EntityImpl() throws Exception
	{
		show = Outside.service(this,"gus06.swing.frame.show2");
		build = Outside.service(this,"factory#gus06.sys.filetool.ext.appmonitoring1.holder");
	}
	
	
	public void e() throws Exception
	{
		Object holder = build.g();
		((E) holder).e();
		Object comp = ((I) holder).i();
		
		show.p(new Object[]{comp,DIM,DISPLAY});
	}
}
