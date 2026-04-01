package a.entity.gus06.sys.fileeditorpersister1.textcomp;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, V, R {

	public String creationDate() {return "20200428";}
	
	public static final String FILENAME = "store.properties";

	public static final String KEY_CARET = "caret";

	private Service readProp;
	private Service writeProp;
	
	private File file;
	private Map map;


	public EntityImpl() throws Exception
	{
		readProp = Outside.service(this,"gus06.file.read.properties.safe");
		writeProp = Outside.service(this,"gus06.file.write.properties");
		
		File dir = (File) Outside.resource(this,"defaultdir");
		file = new File(dir,FILENAME);
		
		map = (Map) readProp.t(file);
		if(map==null) map = new HashMap();
	}
	
	
	public Object r(String key) throws Exception
	{
		Map m = new HashMap();
		
		transfert(m,key,KEY_CARET);
		
		return m;
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		Map m = (Map) obj;
		Iterator it = m.keySet().iterator();
		while(it.hasNext())
		{
			String k = (String) it.next();
			String v = (String) m.get(k);
			
			map.put(k+"_"+key,v);
		}
		
		writeProp.p(new Object[]{file,map});
	}
	
	
	
	private void transfert(Map m, String key, String k)
	{
		String key1 = k+"_"+key;
		if(map.containsKey(key1))
		{
			String v = (String) map.get(key1);
			m.put(k,v);
		}
	}
}