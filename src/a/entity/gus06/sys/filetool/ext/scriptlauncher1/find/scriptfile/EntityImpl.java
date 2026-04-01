package a.entity.gus06.sys.filetool.ext.scriptlauncher1.find.scriptfile;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220608";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File root = (File) o[0];
		String path = (String) o[1];
		String scriptName = (String) o[2];
		
		File dir = new File(root, path.replace(".",File.separator));
		return new File(dir, scriptName);
	}
}