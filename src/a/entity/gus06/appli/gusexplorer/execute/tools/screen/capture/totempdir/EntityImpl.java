package a.entity.gus06.appli.gusexplorer.execute.tools.screen.capture.totempdir;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20180215";}
	

	private Service before;
	private Service captureImage;
	private Service clipboard;
	private Service fromClipboard;

	public EntityImpl() throws Exception
	{
		before = Outside.service(this,"gus06.appli.gusexplorer.execute.tools.screen.beforecapture");
		captureImage = Outside.service(this,"gus06.sys.capturescreen1.capture");
		clipboard = Outside.service(this,"gus06.clipboard.access.image");
		fromClipboard = Outside.service(this,"gus06.appli.gusexplorer.execute.tabs.add.temp.fromclipboard");
	}
	
	public void e() throws Exception
	{
		before.e();
		Object img = captureImage.g();
		if(img==null) return;
		
		clipboard.p(img);
		fromClipboard.e();
	}
}
