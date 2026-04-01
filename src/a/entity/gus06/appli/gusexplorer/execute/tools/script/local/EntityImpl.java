package a.entity.gus06.appli.gusexplorer.execute.tools.script.local;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20250318";}
	
	public static final String START = "gus06/resource/gus/gyem/script/";


	private Service findJar;
	private Service stringFinder;
	private Service listChooser;
	private Service show;

	public EntityImpl() throws Exception
	{
		findJar = Outside.service(this,"gus06.app.jarfile");
		stringFinder = Outside.service(this,"gus06.file.jar.string.finder1");
		listChooser = Outside.service(this,"*gus06.sys.listchooser1.dialog2");
		show = Outside.service(this,"gus06.sys.scriptgusview1.mainpanel.show");
	}
	
	public void e() throws Exception
	{
		File appJar = (File) findJar.g();
		Map map = (Map) stringFinder.t(new Object[]{appJar, START});
		show.p(map);
	}
}