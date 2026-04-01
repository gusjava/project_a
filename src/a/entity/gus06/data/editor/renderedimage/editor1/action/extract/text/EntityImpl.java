package a.entity.gus06.data.editor.renderedimage.editor1.action.extract.text;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231022";}
	
	public static final String DISPLAY = "IMG_txt#Extract text (OCR)";

	private Service perform;
	private Service buildAction;
	private Service clipboard;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.sys.tesseract1.imagetotext");
		buildAction = Outside.service(this,"gus06.swing.action.builder1");
		clipboard = Outside.service(this,"gus06.clipboard.access");
	}
	
	public Object t(Object obj) throws Exception
	{
		Holder holder = new Holder((G) obj);
		return buildAction.t(new Object[]{DISPLAY, holder});
	}
	
	
	private class Holder implements E
	{
		private G getImage;
		
		public Holder(G getImage)
		{this.getImage = getImage;}
		
		public void e() throws Exception
		{
			Object image = getImage.g();
			String text = (String) perform.t(image);
			clipboard.p(text);
		}
	}
}