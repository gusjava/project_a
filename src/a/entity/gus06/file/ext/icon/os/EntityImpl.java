package a.entity.gus06.file.ext.icon.os;

import java.io.File;
import java.util.Map;
import java.util.HashMap;
import javax.swing.Icon;
import a.framework.*;

public class EntityImpl implements Entity, T, R, G {

	public String creationDate() {return "20140806";}
	
	public static final String DIR = "#";

	
	private Service fileToIcon;
	private Service fileSample;
	
	private Map cache;
	
	
	public EntityImpl() throws Exception
	{
		fileToIcon = Outside.service(this,"gus06.file.icon.os");
		fileSample = Outside.service(this,"gus06.file.ext.filesample");
		
		cache = new HashMap();
	}
	
	
	public Object g() throws Exception
	{return r(DIR);}
	
	
	public Object r(String key) throws Exception
	{return t(key);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String ext = (String) obj;
		if(!cache.containsKey(ext))
			cache.put(ext,build(ext));
		return cache.get(ext);
	}
	
	
	private Icon build(String ext) throws Exception
	{
		if(ext.equals(DIR)) return (Icon) fileToIcon.t(fileSample.g());
		
		File file = (File) fileSample.t(ext);
		return (Icon) fileToIcon.t(file);
	}
}
