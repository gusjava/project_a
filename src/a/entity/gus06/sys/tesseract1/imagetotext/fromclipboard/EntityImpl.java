package a.entity.gus06.sys.tesseract1.imagetotext.fromclipboard;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20210607";}


	private Service imageToText;
	private Service fromClipboard;


	public EntityImpl() throws Exception
	{
		imageToText = Outside.service(this,"gus06.sys.tesseract1.imagetotext");
		fromClipboard = Outside.service(this,"gus06.clipboard.access.image");
	}
	
	
	public Object g() throws Exception
	{
		Object img = fromClipboard.g();
		return imageToText.t(img);
	}
}