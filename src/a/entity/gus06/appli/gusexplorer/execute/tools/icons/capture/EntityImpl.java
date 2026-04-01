package a.entity.gus06.appli.gusexplorer.execute.tools.icons.capture;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20201205";}
	

	private Service before;
	private Service captureImage;
	private Service importer;

	public EntityImpl() throws Exception
	{
		before = Outside.service(this,"gus06.appli.gusexplorer.execute.tools.screen.beforecapture");
		captureImage = Outside.service(this,"gus06.sys.captureicon1.capture");
		importer = Outside.service(this,"gus06.appli.gusexplorer.icons.importer");
	}
	
	public void e() throws Exception
	{
		Object data = findData();
		if(data!=null) importer.p(data);
	}
	
	private Object findData() throws Exception
	{
		before.e();
		return captureImage.g();
	}
}