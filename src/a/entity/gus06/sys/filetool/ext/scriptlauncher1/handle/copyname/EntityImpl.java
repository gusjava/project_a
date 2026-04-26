package a.entity.gus06.sys.filetool.ext.scriptlauncher1.handle.copyname;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231025";}

	private Service clipboard;

	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus.x.clipboard.string");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File root = (File) o[0];
		String key = (String) o[1];
		String scriptName = (String) o[2];
		
		clipboard.p(key);
	}
}