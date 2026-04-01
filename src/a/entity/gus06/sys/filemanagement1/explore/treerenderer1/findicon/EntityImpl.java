package a.entity.gus06.sys.filemanagement1.explore.treerenderer1.findicon;

import a.framework.*;
import java.util.Map;
import java.awt.Color;
import java.util.List;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191213";}
	
	public static final String KEY_TYPE = "type";
	public static final String KEY_NAME = "name";
	
	public static final String TYPE_ROOT = "root";
	public static final String TYPE_FILE = "file";
	public static final String TYPE_DIR = "dir";


	private Service findIcon;
	
	private Icon iconRoot;
	private Icon iconDir;
	private Icon iconDir_;
	
	public EntityImpl() throws Exception
	{
		findIcon = Outside.service(this,"gus06.file.filename.icon.t1");
		
		iconRoot = (Icon) Outside.resource(this,"icon#UTIL_disk");
		iconDir = (Icon) Outside.resource(this,"icon#dir");
		iconDir_ = (Icon) Outside.resource(this,"icon#dir_");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof Map) return findIcon((Map) obj,false);
		if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			return findIcon((Map) o[0],(Boolean) o[1]);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private Icon findIcon(Map map, Boolean expanded) throws Exception
	{
		if(map==null) return null;
		if(!map.containsKey(KEY_TYPE)) return null;
		String type = (String) map.get(KEY_TYPE);
		
		if(type.equals(TYPE_ROOT))
		{
			return iconRoot;
		}
		if(type.equals(TYPE_DIR))
		{
			return expanded ? iconDir_ : iconDir;
		}
		if(type.equals(TYPE_FILE))
		{
			String name = (String) map.get(KEY_NAME);
			return (Icon) findIcon.t(name);
		}
		throw new Exception("Invalid type: "+type);
	}
}
