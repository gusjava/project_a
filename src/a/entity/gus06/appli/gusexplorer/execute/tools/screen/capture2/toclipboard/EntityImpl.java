package a.entity.gus06.appli.gusexplorer.execute.tools.screen.capture2.toclipboard;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20210607";}
	

	private Service before;
	private Service captureText;
	private Service clipboard;

	public EntityImpl() throws Exception
	{
		before = Outside.service(this,"gus06.appli.gusexplorer.execute.tools.screen.beforecapture");
		captureText = Outside.service(this,"gus06.sys.tesseract1.imagetotext.capturescreen");
		clipboard = Outside.service(this,"gus06.clipboard.access.string");
	}
	
	public void e() throws Exception
	{
		before.e();
		Object text = captureText.g();
		if(text==null) return;
		
		clipboard.p(text);
	}
}