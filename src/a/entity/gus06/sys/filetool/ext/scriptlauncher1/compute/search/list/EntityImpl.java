package a.entity.gus06.sys.filetool.ext.scriptlauncher1.compute.search.list;

import a.framework.*;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231028";}

	private Service buildListing;
	private Service readFile;
	
	public EntityImpl() throws Exception
	{
		buildListing = Outside.service(this,"gus06.sys.filetool.ext.scriptlauncher1.buildlisting");
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File root = (File) o[0];
		String scriptName = (String) o[1];
		String query = (String) o[2];
		
		query = query.trim().toLowerCase();
		if(query.equals("")) return new ArrayList();
		
		Map map = (Map) buildListing.t(new Object[]{root, scriptName});
		List list = new ArrayList();
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String path = (String) it.next();
			File f = (File) map.get(path);
			
			String s = (String) readFile.t(f);
			if(s.toLowerCase().contains(query)) list.add(path);
		}
		
		Collections.sort(list);
		return list;
	}
}