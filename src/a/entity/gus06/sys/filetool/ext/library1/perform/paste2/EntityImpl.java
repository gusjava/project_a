package a.entity.gus06.sys.filetool.ext.library1.perform.paste2;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201231";}
	
	private Service clipboard;
	private Service pasteFiles;
	private Service pasteItems;

	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.clipboard.access");
		pasteFiles = Outside.service(this,"gus06.sys.filetool.ext.library1.perform.paste2.files");
		pasteItems = Outside.service(this,"gus06.sys.filetool.ext.library1.perform.paste2.items");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		List keys0 = (List) o[1];
		
		Object data = clipboard.g();
		if(data==null) return false;
		
		if(data instanceof List) return pasteFiles.f(new Object[]{map,keys0,data});
		if(data instanceof String) return pasteItems.f(new Object[]{map,keys0,data});
		
		return false;
	}
}