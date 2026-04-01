package a.entity.gus06.dir.perform.searchparent.byname.first;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160904";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File input =(File) o[0];
		String name = (String) o[1];
		
		File parent = input.getParentFile();
		while(parent!=null && !parent.getName().equals(name))
			parent = parent.getParentFile();
		
		return parent;
	}
}
