package a.entity.gus06.sys.tesseract1.imagetotext.printscreen;

import a.framework.*;

public class EntityImpl implements Entity, G, T {

	public String creationDate() {return "20210607";}


	private Service imageToText;
	private Service printScreen;


	public EntityImpl() throws Exception
	{
		imageToText = Outside.service(this,"gus06.sys.tesseract1.imagetotext");
		printScreen = Outside.service(this,"gus06.awt.robot.printscreen");
	}
	
	
	public Object g() throws Exception
	{
		Object img = printScreen.g();
		return imageToText.t(img);
	}
	
	public Object t(Object obj) throws Exception
	{
		Object img = printScreen.t(obj);
		return imageToText.t(img);
	}
}