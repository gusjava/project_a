package a.entity.gus06.sys.dirdoubloon1.perform.deduplicate.group;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20221224";}


	private Service delete;


	public EntityImpl() throws Exception
	{
		delete = Outside.service(this,"gus06.file.op.delete");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		if(!map.containsKey("file")) return false;
		if(map.containsKey("deduplicated")) return false;
		
		File file = (File) map.get("file");
		if(!file.isFile()) return false;
		
		List files = (List) map.get("files");
		
		for(int i=0;i<files.size();i++)
		{
			File f = (File) files.get(i);
			if(!f.equals(file)) delete.p(f);
		}
		map.put("deduplicated",true);
		return true;
	}
}