package a.entity.gus06.sys.filemanagement1.explore.treerenderer1.findforeground;

import a.framework.*;
import java.util.Map;
import java.awt.Color;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191213";}


	public static final int NB_LIMIT = 1000;
	
	public static final String KEY_TYPE = "type";
	public static final String KEY_SIZE = "size";
	public static final String KEY_MD5 = "md5";
	public static final String KEY_CHILDREN = "children";
	public static final String KEY_FILENB = "fileNb";
	
	public static final String TYPE_FILE = "file";
	public static final String TYPE_DIR = "dir";
	
	public static final String FAILED_MD5 = "###";
	
	
	public static final Color COLOR_EMPTY = Color.RED;
	public static final Color COLOR_BIG = Color.GREEN;
	public static final Color COLOR_FAILED = Color.LIGHT_GRAY;

	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return Color.BLACK;
		
		Map map = (Map) obj;
		if(!map.containsKey(KEY_TYPE)) return Color.BLACK;
		
		String type = (String) map.get(KEY_TYPE);
		if(type.equals(TYPE_DIR))
		{
			List children = (List) map.get(KEY_CHILDREN);
			if(children.isEmpty()) return COLOR_EMPTY;
			if(children.size()>=NB_LIMIT) return COLOR_BIG;
			
			if(!map.containsKey(KEY_FILENB)) return Color.BLACK;
			long fileNb = (long) map.get(KEY_FILENB);
			if(fileNb==0) return Color.RED;
			
			return Color.BLACK;
		}
		if(type.equals(TYPE_FILE))
		{
			String md5 = (String) map.get(KEY_MD5);
			if(md5.equals(FAILED_MD5)) return COLOR_FAILED;
			
			long size = (long) map.get(KEY_SIZE);
			if(size==0) return COLOR_EMPTY;
			
			return Color.BLACK;
		}
		return Color.BLACK;
	}
}
