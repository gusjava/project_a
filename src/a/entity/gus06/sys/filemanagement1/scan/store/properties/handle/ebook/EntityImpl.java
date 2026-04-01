package a.entity.gus06.sys.filemanagement1.scan.store.properties.handle.ebook;

import a.framework.*;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191204";}


	private Service ebookMap1;

	public EntityImpl() throws Exception
	{
		ebookMap1 = Outside.service(this,"gus06.file.info.ebook.map1");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		handleAsEbook(o[0],(Map) o[1],(File) o[2]);
	}
	
	private void handleAsEbook(Object engine, Map prop, File file)
	{
		try
		{
			Map ebookMap = (Map) ebookMap1.t(file);
			if(ebookMap==null) return;
			
			Iterator it = ebookMap.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				String value = (String) ebookMap.get(key);
				prop.put("ebook."+key.toLowerCase(),value);
			}
			
		}
		catch(Exception e)
		{
			Outside.err(this,"handleAsEbook(Object,Map,File)",e);
			prop.put("ebook.error",e.toString());
		}
	}
}
