package a.entity.gus06.sys.tesseract1.imagetotext.capturescreen;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20210607";}


	private Service imageToText;
	private Service captureScreen;


	public EntityImpl() throws Exception
	{
		imageToText = Outside.service(this,"gus06.sys.tesseract1.imagetotext");
		captureScreen = Outside.service(this,"gus06.sys.capturescreen1.capture");
	}
	
	
	public Object g() throws Exception
	{
		Object img = captureScreen.g();
		return imageToText.t(img);
	}
}