package a.entity.gus06.appli.usbwebprint.usbkey.analyze;

import a.framework.*;
import java.util.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140910";}

	public static final String KEY_DESC = "description";
	public static final String KEY_DRIVE = "drive";
	public static final String KEY_ROOT = "root";
	public static final String KEY_MODE = "mode";
	


	private Service description;
	private Service modeFinder;
	
	private Map modes;
	private Map usbKeyMap;
	
	
	public EntityImpl() throws Exception
	{
		description = Outside.service(this,"gus06.dir.hdd.description.build");
		modeFinder = Outside.service(this,"gus06.appli.usbwebprint.usbkey.modes");
		
		modes = (Map) modeFinder.g();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		usbKeyMap = new HashMap();
		
		boolean found = findRoot(f);
		if(!found) return null;
		
		String desc = (String) description.t(f);
		
		put(KEY_DESC,desc);
		put(KEY_DRIVE,f);
		return usbKeyMap;
	}
	
	
	
	private boolean findRoot(File f) throws Exception
	{
		Iterator it = modes.keySet().iterator();
		while(it.hasNext())
		{
			String mode = (String) it.next();
			T t = (T) modes.get(mode);
			
			File root = (File) t.t(f);
			if(root!=null)
			{
				put(KEY_ROOT,root);
				put(KEY_MODE,mode);
				return true;
			}
		}
		return false;
	}
	
	
	private void put(String key, Object value)
	{usbKeyMap.put(key,value);}
}
