package a.entity.gus06.appli.gusexplorer.execute.tools.icons.fromclipboard;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20201206";}
	

	private Service importer;
	private Service clipboardFile;
	private Service clipboardImage;
	private Service clipboardString;
	private Service stringToUrl;

	public EntityImpl() throws Exception
	{
		importer = Outside.service(this,"gus06.appli.gusexplorer.icons.importer");
		clipboardFile = Outside.service(this,"gus06.clipboard.access.file");
		clipboardImage = Outside.service(this,"gus.x.clipboard.image");
		clipboardString = Outside.service(this,"gus.x.clipboard.string");
		stringToUrl = Outside.service(this,"gus.y.convert1.stringtourl");
	}
	
	public void e() throws Exception
	{
		Object data = findData();
		if(data!=null) importer.p(data);
	}
	
	private Object findData() throws Exception
	{
		Object data = clipboardImage.g();
		if(data!=null) return data;
		
		data = clipboardFile.g();
		if(data!=null) return data;
		
		data = clipboardString.g();
		if(data!=null) return stringToUrl.t(data);
		
		return null;
	}
}
