package a.entity.gus06.sys.filemanagement1.gui.detailpanel.dir.doubloons.compute;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250612";}
	
	public static final String KEY_LOCATION = "location";
	public static final String KEY_NAME = "name";
	public static final String KEY_SIZE = "size";

	private Service compute2;

	public EntityImpl() throws Exception
	{
		compute2 = Outside.service(this,"gus06.sys.filemanagement1.gui.detailpanel.dir.doubloons.compute2");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map selected = (Map) obj;
		
		String root = (String) get(selected, KEY_LOCATION);
		int rootLen = root.length();
		
		Map map2 = (Map) compute2.t(selected);
		
		Map output = new HashMap();
		Iterator it = map2.keySet().iterator();
		while(it.hasNext())
		{
			String md5 = (String) it.next();
			List list = (List) map2.get(md5);
			
			if(list.size()>1)
			{
				Map m0 = (Map) list.get(0);
				int nb = list.size();
				Long size = (Long) get(m0, KEY_SIZE);
				long lost = size * (nb-1);
				
				String name0 = (String) get(m0, KEY_NAME);
				String location0 = (String) get(m0, KEY_LOCATION);
				String path0 = location0.substring(rootLen)+File.separator+name0;
				
				List paths = new ArrayList();
				for(int i=0;i<nb;i++)
				{
					Map m = (Map) list.get(i);
					String location = (String) get(m, KEY_LOCATION);
					String name = (String) get(m, KEY_NAME);
					String path = location.substring(rootLen)+File.separator+name;
					paths.add(path);
				}
				
				Map infos = new HashMap();
				infos.put("paths",paths);
				infos.put("path",path0);
				infos.put("md5",md5);
				infos.put("size",size);
				infos.put("nb",nb);
				infos.put("lost",lost);
				
				output.put(md5, infos);
			}
		}
		return output;
	}
	
	private Object get(Map m, String key)
	{return m.containsKey(key) ? m.get(key) : null;}
}