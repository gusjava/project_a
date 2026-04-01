package a.entity.gus06.dir.perform.searchparent.byname.all;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160904";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File input =(File) o[0];
		String name = (String) o[1];
		
		List found = new ArrayList();
		
		File parent = input.getParentFile();
		while(parent!=null)
		{
			if(parent.getName().equals(name)) found.add(parent);
			parent = parent.getParentFile();
		}
		return found;
	}
}
