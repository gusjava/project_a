package a.entity.gus06.data.editor.renderedimage.editor1.action.rotate180;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151007";}
	
	public static final String DISPLAY = "IMG_rotation_180#Rotate 180";

	private Service perform;
	private Service buildAction;
	private Service wrap;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.awt.renderedimage.transform.rotate180");
		buildAction = Outside.service(this,"gus06.swing.action.builder1");
		wrap = Outside.service(this,"gus06.feature.wrap.gtp.e");
	}
	
	public Object t(Object obj) throws Exception
	{
		E ex = (E) wrap.t(new Object[]{obj,perform,obj});
		return buildAction.t(new Object[]{DISPLAY,ex});
	}
}
