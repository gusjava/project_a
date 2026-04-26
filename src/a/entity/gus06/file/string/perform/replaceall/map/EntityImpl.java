package a.entity.gus06.file.string.perform.replaceall.map;

import java.io.File;
import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220222";}


	private Service readFile;
	private Service writeFile;
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		writeFile = Outside.service(this,"gus.x.file.string.write");
	}


	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Map map = (Map) o[1];
		
		String content = (String) readFile.t(file);
		if(content==null) return;
		
		String contentMem = content;
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) map.get(key);
			
			content = content.replace(key,value);
		}
		if(!content.equals(contentMem))
		writeFile.p(new Object[]{file, content});
	}
}