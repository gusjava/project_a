package a.entity.gus06.appli.gusexplorer.execute.tools.colors.picker;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20230205";}
	
	public static final String DISPLAY = "ACTION_pickColor#Color picker";

	
	private Service editor;
	private Service perform;

	public EntityImpl() throws Exception
	{
		editor = Outside.service(this,"*gus06.data.editor.color.editor1");
		perform = Outside.service(this,"gus06.swing.frame.show");
	}
	
	public void e() throws Exception
	{
		perform.v(DISPLAY, editor);
	}
}