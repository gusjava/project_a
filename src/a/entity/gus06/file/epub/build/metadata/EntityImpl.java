package a.entity.gus06.file.epub.build.metadata;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191010";}

	public static final String KEY_AUTHOR = "AUTHOR";
	public static final String KEY_LANGUAGE = "LANGUAGE";
	public static final String KEY_PUBLISHING_DATE = "PUBLISHING_DATE";
	public static final String KEY_PUBLISHER = "PUBLISHER";
	public static final String KEY_IMPRINT = "IMPRINT";
	public static final String KEY_DESCRIPTION = "DESCRIPTION";
	public static final String KEY_TITLE = "TITLE";
	public static final String KEY_ISBN = "ISBN";
	
	
	public Object t(Object obj) throws Exception
	{
		Map data = (Map) obj;
		Map map_dc = (Map) data.get("map_dc");
		
		Map map = new HashMap();
		
		if(map_dc==null) return map;
		
		transfert(map_dc,map,"title",				KEY_TITLE);
		transfert(map_dc,map,"language",			KEY_LANGUAGE);
		transfert(map_dc,map,"date",				KEY_PUBLISHING_DATE);
		transfert(map_dc,map,"publisher",			KEY_PUBLISHER);
		transfert(map_dc,map,"description",			KEY_DESCRIPTION);
		
		transfertStartsWith(map_dc,map,"creator",		KEY_AUTHOR);
		transfert(map_dc,map,"identifier opf:scheme=\"ISBN\"",	KEY_ISBN);
		
		
//		transfert(map_dc,map,"IMPRINT");
//		transfert(map_dc,map,"SUBJECT");
//		transfert(map_dc,map,"RIGHTS");
//		transfert(map_dc,map,"ASIN");


		
		
		return map;
	}
	
	
	private void transfert(Map m1, Map m2, String key)
	{if(m1.containsKey(key)) m2.put(key,m1.get(key));}
	
	private void transfert(Map m1, Map m2, String key1, String key2)
	{if(m1.containsKey(key1)) m2.put(key2,m1.get(key1));}
	
	
	private void transfertStartsWith(Map m1, Map m2, String key1, String key2)
	{
		Iterator it = m1.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(key.startsWith(key1+" "))
			{
				String value = (String) m1.get(key);
				m2.put(key2, value.trim());
				return;
			}
		}
	}
}