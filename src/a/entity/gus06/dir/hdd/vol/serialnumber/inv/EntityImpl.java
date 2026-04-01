package a.entity.gus06.dir.hdd.vol.serialnumber.inv;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191126";}


	private Service find;

	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.dir.hdd.vol.serialnumber");
	}
	
	public Object t(Object obj) throws Exception
	{
		String serial = (String) obj;
		if(serial==null) return null;
		
		File[] roots = File.listRoots();
		for(File root : roots)
		{
			String serial1 = (String) find.t(root);
			if(serial1.equals(serial)) return root;
		}
		return null;
	}
}
