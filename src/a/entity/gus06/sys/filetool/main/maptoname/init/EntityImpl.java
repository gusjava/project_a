package a.entity.gus06.sys.filetool.main.maptoname.init;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150324";}

	public static final String PATH_THIS = "path.this";
	public static final String EXT_DEFAULT = "gus.sys.filetool.ext.default0";
	
	
	
	private Service chooser;
	private Service extmap;

	public EntityImpl() throws Exception
	{
		chooser = Outside.service(this,"gus06.input.choose.dialog");
		extmap = Outside.service(this,"gus06.sys.filetool.extmap");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String name1 = findFromName(map);
		if(name1!=null) return name1;
		
		String name2 = findFromChooser();
		if(name2!=null) return name2;
		
		return EXT_DEFAULT;
	}
	
	
	
	
	
	private String findFromName(Map map) throws Exception
	{
		if(!map.containsKey(PATH_THIS))
			throw new Exception("Key not found: "+PATH_THIS);
		
		String path = (String) map.get(PATH_THIS);
		File file = new File(path);
		if(!file.isFile())
			throw new Exception("Current file not found: "+file);
		
		String name = file.getName();
		String[] n = name.split("\\.");
		if(n.length<2) throw new Exception("Invalid name for tool file: "+name);
		
		String key = n[n.length-2];
		return (String) extmap.r(key);
	}
	
	
	
	
	private String findFromChooser() throws Exception
	{
		String message = "Please, choose a TOOL entity";
		String title = "Tool chooser";
		String[] list = (String[]) extmap.g();
		
		return (String) chooser.t(new Object[]{message,title,list});	
	}
}
