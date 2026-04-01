package a.entity.gus06.sys.fullscreen1.build.action;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191121";}

	public static final String DISPLAY = "ACTION_fullScreen#Full screen";


	private Service buildAction;
	private Service handler;
	private Service wrap;


	public EntityImpl() throws Exception
	{
		buildAction = Outside.service(this,"gus06.swing.action.builder1");
		handler = Outside.service(this,"gus06.sys.fullscreen1.main");
		wrap = Outside.service(this,"gus06.feature.wrap.po.e");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		E ex = (E) wrap.t(new Object[]{handler,obj});
		return buildAction.t(new Object[]{DISPLAY,ex});
	}
}
