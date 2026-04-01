package a.entity.gus06.file.image.perform.edition1.dialog;

import a.framework.*;
import java.awt.image.RenderedImage;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151005";}


	private Service dialogOkCancel;
	private Service imageEditor;


	public EntityImpl() throws Exception
	{
		dialogOkCancel = Outside.service(this,"gus06.swing.dialog.blocked1.okcancel");
		imageEditor = Outside.service(this,"*gus06.data.editor.renderedimage.editor1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		RenderedImage image = (RenderedImage) obj;
		
		imageEditor.p(image);
		
		dialogOkCancel.v("width","400");
		dialogOkCancel.v("height","400");
		
		boolean ok = dialogOkCancel.f(imageEditor.i());
		return ok? imageEditor.g():null;
	}
}
