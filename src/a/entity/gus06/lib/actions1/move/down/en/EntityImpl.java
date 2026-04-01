package a.entity.gus06.lib.actions1.move.down.en;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190808";}

	public static final String DISPLAY = "ACTION_move_down#Move down";

	private Service buildAction;
	
	public EntityImpl() throws Exception
	{buildAction = Outside.service(this,"gus06.swing.action.builder1");}
	
	public Object t(Object obj) throws Exception
	{return buildAction.t(new Object[]{DISPLAY,obj});}
}