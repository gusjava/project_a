package a.entity.gus06.sys.filetool.ext.library1.perform.paste3;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;
import java.awt.Image;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20220602";}
	
	private Service clipboard;
	private Service pasteFiles;
	private Service pasteString;
	private Service pasteImage;

	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.clipboard.access");
		pasteFiles = Outside.service(this,"gus06.sys.filetool.ext.library1.perform.paste3.files");
		pasteString = Outside.service(this,"gus06.sys.filetool.ext.library1.perform.paste3.string");
		pasteImage = Outside.service(this,"gus06.sys.filetool.ext.library1.perform.paste3.image");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		Object data = clipboard.g();
		if(data==null) return false;
		
		if(data instanceof List) return pasteFiles.f(new Object[]{map,data});
		if(data instanceof String) return pasteString.f(new Object[]{map,data});
		if(data instanceof Image) return pasteImage.f(new Object[]{map,data});
		
		return false;
	}
}
