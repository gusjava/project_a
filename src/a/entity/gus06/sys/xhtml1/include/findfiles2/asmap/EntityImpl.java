package a.entity.gus06.sys.xhtml1.include.findfiles2.asmap;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220908";}
	
	public static final String KEY_FILE = "file";
	public static final String KEY_CONTENT = "content";
	public static final String KEY_LOCATION = "location";
	public static final String KEY_CHILDREN = "children";
	public static final String KEY_TYPE = "type";
	private Service fileToLocation;
	private Service readFile;
	
	private Service findUiIncludes;
	private Service findUiDecorates;
	private Service findUiCompositions;
	
	public EntityImpl() throws Exception
	{
		fileToLocation = Outside.service(this,"gus06.sys.xhtml1.filetolocation");
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		
		findUiIncludes = Outside.service(this,"gus06.sys.xhtml1.include.findfiles.ui_include");
		findUiDecorates = Outside.service(this,"gus06.sys.xhtml1.include.findfiles.ui_decorate");
		findUiCompositions = Outside.service(this,"gus06.sys.xhtml1.include.findfiles.ui_composition");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof File) return handleFile((File) obj);
		if(obj instanceof Object[]) return handleArray((Object[]) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Map handleFile(File file) throws Exception
	{
		String text = (String) readFile.t(file);
		return handle(file,text);
	}
	
	private Map handleArray(Object[] array) throws Exception
	{
		if(array.length!=2) throw new Exception("Wrong data number: "+array.length);
		return handle((File) array[0],(String) array[1]);
	}
	
	private Map handle(File file, String content) throws Exception
	{
		String path = file.getAbsolutePath();
		String location = (String) fileToLocation.t(file);
		if(location==null) return null;
		
		List ancestors = new ArrayList();
		ancestors.add(path);
		
		Map m = new HashMap();
		m.put(KEY_FILE, file);
		m.put(KEY_TYPE, "origin");
		m.put(KEY_CONTENT, content);
		m.put(KEY_LOCATION, location);
		m.put(KEY_CHILDREN, buildChildren(file, content, ancestors));
		
		return m;
	}
	
	private List buildChildren(File file, String content, List ancestors) throws Exception
	{
		List list = new ArrayList();
		
		{
			Map children = (Map) findUiCompositions.t(new Object[]{file,content});
			List keys = new ArrayList(children.keySet());
			Collections.sort(keys);
			
			for(int i=0;i<keys.size();i++)
			{
				String key = (String) keys.get(i);
				File f = (File) children.get(key);
				String p = f.getAbsolutePath();
				
				if(ancestors.contains(p)) 
					throw new Exception("Infinite loop detected with xhtml file: "+p);
			
				Map m = new HashMap();
				list.add(m);
				
				String c = (String) readFile.t(f);
				
				m.put(KEY_FILE, f);
				m.put(KEY_TYPE, "composition");
				m.put(KEY_CONTENT, c);
				m.put(KEY_LOCATION, key);
				m.put(KEY_CHILDREN, buildChildren(f, c, buildAncestors(ancestors, p)));
			}
		}
		
		{
			Map children = (Map) findUiDecorates.t(new Object[]{file,content});
			List keys = new ArrayList(children.keySet());
			Collections.sort(keys);
			
			for(int i=0;i<keys.size();i++)
			{
				String key = (String) keys.get(i);
				File f = (File) children.get(key);
				String p = f.getAbsolutePath();
				
				if(ancestors.contains(p)) 
					throw new Exception("Infinite loop detected with xhtml file: "+p);
			
				Map m = new HashMap();
				list.add(m);
				
				String c = (String) readFile.t(f);
				
				m.put(KEY_FILE, f);
				m.put(KEY_TYPE, "decorate");
				m.put(KEY_CONTENT, c);
				m.put(KEY_LOCATION, key);
				m.put(KEY_CHILDREN, buildChildren(f, c, buildAncestors(ancestors, p)));
			}
		}
		
		{
			Map children = (Map) findUiIncludes.t(new Object[]{file,content});
			List keys = new ArrayList(children.keySet());
			Collections.sort(keys);
			
			for(int i=0;i<keys.size();i++)
			{
				String key = (String) keys.get(i);
				File f = (File) children.get(key);
				String p = f.getAbsolutePath();
				
				if(ancestors.contains(p)) 
					throw new Exception("Infinite loop detected with xhtml file: "+p);
			
				Map m = new HashMap();
				list.add(m);
				
				String c = (String) readFile.t(f);
				
				m.put(KEY_FILE, f);
				m.put(KEY_TYPE, "include");
				m.put(KEY_CONTENT, c);
				m.put(KEY_LOCATION, key);
				m.put(KEY_CHILDREN, buildChildren(f, c, buildAncestors(ancestors, p)));
			}
		}
		
		return list;
	}
	
	private List buildAncestors(List previous, String p)
	{
		List newList = new ArrayList(previous);
		newList.add(p);
		return newList;
	}
}