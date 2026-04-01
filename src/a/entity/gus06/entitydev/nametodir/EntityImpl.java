package a.entity.gus06.entitydev.nametodir;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150525";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File root = (File) o[0];
		String name = (String) o[1];
		
		String relPath = ("gus06.entity."+name).replace(".",File.separator);
		return new File(root,relPath);
	}
}
