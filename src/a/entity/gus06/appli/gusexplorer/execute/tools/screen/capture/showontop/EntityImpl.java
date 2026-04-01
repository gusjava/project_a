package a.entity.gus06.appli.gusexplorer.execute.tools.screen.capture.showontop;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20180215";}
	

	private Service before;
	private Service captureImage;
	private Service showInFrame;
	private Service now;

	public EntityImpl() throws Exception
	{
		before = Outside.service(this,"gus06.appli.gusexplorer.execute.tools.screen.beforecapture");
		captureImage = Outside.service(this,"gus06.sys.capturescreen1.capture");
		showInFrame = Outside.service(this,"gus06.image.show.inframe.alwaysontop");
		now = Outside.service(this,"gus06.time.now.hhmmss1");
	}
	
	public void e() throws Exception
	{
		before.e();
		Object img = captureImage.g();
		if(img==null) return;
		
		String title = "CAPTURE_showOnTop#Capture at "+now.g();
		showInFrame.v(title,img);
	}
}