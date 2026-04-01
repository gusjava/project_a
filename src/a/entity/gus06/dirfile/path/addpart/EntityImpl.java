package a.entity.gus06.dirfile.path.addpart;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180328";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object data = o[1];
		
		String newPath = file.getAbsolutePath()+data;
		return new File(newPath);
	}
}
