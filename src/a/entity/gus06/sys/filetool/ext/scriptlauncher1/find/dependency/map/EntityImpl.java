package a.entity.gus06.sys.filetool.ext.scriptlauncher1.find.dependency.map;

import a.framework.*;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231027";}

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
		String targetPath = (String) o[2];
		
		Map map = (Map) buildListing.t(new Object[]{root, scriptName});
		Map map1 = new HashMap();
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String path = (String) it.next();
			File f = (File) map.get(path);
			
			String s = (String) readFile.t(f);
			if(s.contains("\""+targetPath+"\"")) map1.put(path, f);
			else if(s.contains("'"+targetPath+"'")) map1.put(path, f);
		}
		return map1;
	}
}
