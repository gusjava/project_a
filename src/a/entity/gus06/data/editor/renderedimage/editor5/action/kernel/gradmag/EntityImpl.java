package a.entity.gus06.data.editor.renderedimage.editor5.action.kernel.gradmag;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191120";}
	
	public static final String DISPLAY = "IMG_kernel_gradmag#Gradient magnitude";

	private Service perform;
	private Service buildAction;
	private Service wrap;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.awt.renderedimage.transform.kernel.gradientmagnitude");
		buildAction = Outside.service(this,"gus06.swing.action.builder1");
		wrap = Outside.service(this,"gus06.feature.wrap.gtp.e");
	}
	
	public Object t(Object obj) throws Exception
	{
		E ex = (E) wrap.t(new Object[]{obj,perform,obj});
		return buildAction.t(new Object[]{DISPLAY,ex});
	}
}
