package a.entity.gus06.appli.gusexplorer.execute.tools.screen.capture2.totempdir;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20210607";}
	

	private Service before;
	private Service captureText;
	private Service clipboard;
	private Service fromClipboard;

	public EntityImpl() throws Exception
	{
		before = Outside.service(this,"gus06.appli.gusexplorer.execute.tools.screen.beforecapture");
		captureText = Outside.service(this,"gus06.sys.tesseract1.imagetotext.capturescreen");
		clipboard = Outside.service(this,"gus.x.clipboard.string");
		fromClipboard = Outside.service(this,"gus06.appli.gusexplorer.execute.tabs.add.temp.fromclipboard");
	}
	
	public void e() throws Exception
	{
		before.e();
		Object text = captureText.g();
		if(text==null) return;
		
		clipboard.p(text);
		fromClipboard.e();
	}
}