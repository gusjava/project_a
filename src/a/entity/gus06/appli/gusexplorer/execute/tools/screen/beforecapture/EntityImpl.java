package a.entity.gus06.appli.gusexplorer.execute.tools.screen.beforecapture;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20200318";}
	
	public static final String KEY = "app.frame.oncapture.minimize";


	private Service minimizeFrame;
	private Service propBoolDF;

	public EntityImpl() throws Exception
	{
		minimizeFrame = Outside.service(this,"gus06.app.mainframe.perform.minimize");
		propBoolDF = Outside.service(this,"propbool_df");
	}
	
	public void e() throws Exception
	{
		if(!propBoolDF.f(KEY)) return;
		minimizeFrame.e();
	}
}
