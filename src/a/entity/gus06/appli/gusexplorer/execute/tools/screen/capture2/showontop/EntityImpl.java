package a.entity.gus06.appli.gusexplorer.execute.tools.screen.capture2.showontop;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20210607";}
	

	private Service before;
	private Service captureText;
	private Service showInFrame;
	private Service now;

	public EntityImpl() throws Exception
	{
		before = Outside.service(this,"gus06.appli.gusexplorer.execute.tools.screen.beforecapture");
		captureText = Outside.service(this,"gus06.sys.tesseract1.imagetotext.capturescreen");
		showInFrame = Outside.service(this,"gus06.string.show.inframe.editable");
		now = Outside.service(this,"gus06.time.now.hhmmss1");
	}
	
	public void e() throws Exception
	{
		before.e();
		Object text = captureText.g();
		if(text==null) return;
		
		String title = "CAPTURE2_showOnTop#Capture at "+now.g();
		showInFrame.v(title,text);
	}
}