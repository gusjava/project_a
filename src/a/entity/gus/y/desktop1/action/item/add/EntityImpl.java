package a.entity.gus.y.desktop1.action.item.add;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191121";}

	public static final String DISPLAY = "SWING_JFrame_add#Add new item";


	private Service handler;
	private Service buildAction;
	private Service wrap;


	public EntityImpl() throws Exception
	{
		handler = Outside.service(this,"gus.y.desktop1.execute.item.add");
		buildAction = Outside.service(this,"gus06.swing.action.builder1");
		wrap = Outside.service(this,"gus06.feature.wrap.po.e");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		E ex = (E) wrap.t(new Object[]{handler,obj});
		return buildAction.t(new Object[]{DISPLAY,ex});
	}
}
