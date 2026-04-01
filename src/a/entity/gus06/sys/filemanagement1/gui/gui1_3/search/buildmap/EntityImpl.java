package a.entity.gus06.sys.filemanagement1.gui.gui1_3.search.buildmap;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200322";}
	
	public static final String KEY_TYPE = "type";
	public static final String KEY_NAME = "name";
	public static final String KEY_SIZE = "size";
	public static final String KEY_MD5 = "md5";
	public static final String KEY_MIME = "mime";
	public static final String KEY_MODIFIED = "modified";
	public static final String KEY_LOCATION = "location";
	public static final String KEY_ROOTNAME = "rootName";
	
	public static final String TYPE_FILE = "file";


	private Service newMap;
	
	public EntityImpl() throws Exception
	{
		newMap = Outside.service(this,"gus06.map.factory.silentmap");
	}
	
	public Object t(Object obj) throws Exception
	{
		String[] data = (String[]) obj;
		if(data.length!=7) throw new Exception("Wrong data number: "+data.length);
		
		String rootName = data[0];
		String location = data[1];
		String fileName = data[2];
		String size = data[3];
		String modified = data[4];
		String md5 = data[5];
		String mime = data[6];
		
		Map map = (Map) newMap.g();
		
		map.put(KEY_TYPE,TYPE_FILE);
		map.put(KEY_ROOTNAME,rootName);
		map.put(KEY_LOCATION,location);
		map.put(KEY_NAME,fileName);
		map.put(KEY_SIZE,Long.parseLong(size));
		map.put(KEY_MODIFIED,modified);
		map.put(KEY_MD5,md5);
		map.put(KEY_MIME,mime);
		
		return map;
	}
}
