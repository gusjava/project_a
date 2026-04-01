package a.entity.gus06.file.mobi.build.metadata;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191008";}

	public static final String KEY_AUTHOR = "AUTHOR";
	public static final String KEY_PUBLISHER = "PUBLISHER";
	public static final String KEY_IMPRINT = "IMPRINT";
	public static final String KEY_DESCRIPTION = "DESCRIPTION";
	public static final String KEY_TITLE = "TITLE";
	public static final String KEY_ISBN = "ISBN";
	
	
	public Object t(Object obj) throws Exception
	{
		Map data = (Map) obj;
		Map exthMap = (Map) data.get("exthMap");
		
		Map map = new HashMap();
		
		transfert(exthMap,map,KEY_AUTHOR);
		transfert(exthMap,map,KEY_PUBLISHER);
		transfert(exthMap,map,KEY_IMPRINT);
		transfert(exthMap,map,KEY_DESCRIPTION);
		transfert(exthMap,map,KEY_ISBN);
		
		transfert(exthMap,map,"SUBJECT");
		transfert(exthMap,map,"UPDATED_TITLE",KEY_TITLE);
		transfert(exthMap,map,"PUBLISHING_DATE");
		transfert(exthMap,map,"RIGHTS");
		transfert(exthMap,map,"ASIN");
		transfert(exthMap,map,"LANGUAGE");
		
		return map;
	}
	
	
	private void transfert(Map m1, Map m2, String key)
	{if(m1.containsKey(key)) m2.put(key,m1.get(key));}
	
	private void transfert(Map m1, Map m2, String key1, String key2)
	{if(m1.containsKey(key1)) m2.put(key2,m1.get(key1));}
}
