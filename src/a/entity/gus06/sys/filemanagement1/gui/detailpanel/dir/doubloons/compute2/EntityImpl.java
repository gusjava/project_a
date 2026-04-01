package a.entity.gus06.sys.filemanagement1.gui.detailpanel.dir.doubloons.compute2;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250613";}
	
	public static final String KEY_MD5 = "md5";
	public static final String KEY_TYPE = "type";
	public static final String KEY_CHILDREN = "children";
	
	public static final String TYPE_ROOT = "root";
	public static final String TYPE_DIR = "dir";
	public static final String TYPE_FILE = "file";
	
	
	public Object t(Object obj) throws Exception
	{
		Map selected = (Map) obj;
		Map output = new HashMap();
		handle(output, selected);
		return output;
	}
	
	private void handle(Map output, Map map) throws Exception
	{
		String type = (String) get(map, KEY_TYPE);
		
		if(type.equals(TYPE_ROOT)) handleRoot(output, map);
		else if(type.equals(TYPE_DIR)) handleDir(output, map);
		else if(type.equals(TYPE_FILE))  handleFile(output, map);
		else throw new Exception("Unsupported type: "+type);
	}
	
	private void handleRoot(Map output, Map dirMap) throws Exception
	{
		List children = (List) get(dirMap, KEY_CHILDREN);
		if(children!=null) for(int i=0;i<children.size();i++)
		handle(output, (Map) children.get(i));
	}
	
	private void handleDir(Map output, Map dirMap) throws Exception
	{
		List children = (List) get(dirMap, KEY_CHILDREN);
		if(children!=null) for(int i=0;i<children.size();i++)
		handle(output, (Map) children.get(i));
	}
	
	private void handleFile(Map output, Map fileMap)
	{
		String md5 = (String) get(fileMap, KEY_MD5);
		if(!output.containsKey(md5)) output.put(md5, new ArrayList());
		((List) output.get(md5)).add(fileMap);
	}
	
	private Object get(Map m, String key)
	{return m.containsKey(key) ? m.get(key) : null;}
}