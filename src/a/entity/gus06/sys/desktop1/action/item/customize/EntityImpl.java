package a.entity.gus06.sys.desktop1.action.item.customize;

import a.framework.*;
import javax.swing.Action;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260114";}

	public static final String DISPLAY = "SWING_JFrame_settings#Customize selected item";

	private Service handler;
	private Service buildAction;
	private Service wrap;
	private Service enabler;

	public EntityImpl() throws Exception
	{
		handler = Outside.service(this,"gus06.sys.desktop1.execute.item.remove");
		buildAction = Outside.service(this,"gus06.swing.action.builder1");
		wrap = Outside.service(this,"gus06.feature.wrap.po.e");
		enabler = Outside.service(this,"gus06.sys.desktop1.action.item.remove.enabler");
	}
	
	public Object t(Object obj) throws Exception
	{
		E ex = (E) wrap.t(new Object[]{handler,obj});
		Action action = (Action) buildAction.t(new Object[]{DISPLAY,ex});
		enabler.p(new Object[]{action,obj});
		return action;
	}
}
