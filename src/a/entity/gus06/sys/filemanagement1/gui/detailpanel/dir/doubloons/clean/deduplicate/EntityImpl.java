package a.entity.gus06.sys.filemanagement1.gui.detailpanel.dir.doubloons.clean.deduplicate;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20250614";}

	private Service delete;


	public EntityImpl() throws Exception
	{
		delete = Outside.service(this,"gus.x.file.op.delete");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Map map = (Map) o[1];
		
		if(!map.containsKey("path")) return false;
		if(map.containsKey("deduplicated")) return false;
		
		String path = (String) map.get("path");
		List paths = (List) map.get("paths");
		
		File file = new File(dir, path);
		if(!file.isFile()) return false;
		
		int done = 0;
		for(int i=0;i<paths.size();i++)
		{
			String p = (String) paths.get(i);
			if(!p.equals(path))
			{
				File f = new File(dir, p);
				if(f.isFile())
				{
					delete.p(f);
					done++;
				}
			}
		}
		if(done==0) return false;
		map.put("deduplicated",true);
		return true;
	}
}