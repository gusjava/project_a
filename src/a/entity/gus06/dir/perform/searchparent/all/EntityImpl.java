package a.entity.gus06.dir.perform.searchparent.all;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160829";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File input =(File) o[0];
		F filter = (F) o[1];
		
		List found = new ArrayList();
		
		File parent = input.getParentFile();
		while(parent!=null)
		{
			if(filter.f(parent)) found.add(parent);
			parent = parent.getParentFile();
		}
		return found;
	}
}
