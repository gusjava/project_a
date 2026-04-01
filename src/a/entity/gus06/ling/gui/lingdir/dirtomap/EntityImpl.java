package a.entity.gus06.ling.gui.lingdir.dirtomap;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140811";}


	public static final FileFilter FILEFILTER = new FileFilter() {
		public boolean accept(File f) {return f.isFile();}
	};
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		File dir = (File) obj;
		File[] ff = dir.listFiles(FILEFILTER);
		if(ff==null) return null;
		
		Map map = new HashMap();
		for(File f:ff) handle(map,f);
		return map;
	}
	
	
	private void handle(Map map, File f) throws Exception
	{
		String name = f.getName();
		String[] n = name.split("_");
		if(n.length!=2) throw new Exception("Invalid ling file name: "+name);
		
		String id = n[0];
		String lang = n[1];
		
		if(!map.containsKey(id)) map.put(id,new HashMap());
		Map resource = (Map) map.get(id);
		resource.put(lang,f);
	}
}
