package a.entity.gus06.sys.xhtml1.include.findfiles.ui_decorate;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220908";}


	private Service findRoot;
	private Service readFile;
	private Service resolveFile;
	private Service extract1;

	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.xhtml1.webroot.find");
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		resolveFile = Outside.service(this,"gus06.sys.xhtml1.resolve.file");
		extract1 = Outside.service(this,"gus06.sys.xhtml1.extract.ui_decorate.template");
	}
	
	
	public Object t(Object obj) throws Exception
	{
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
	
	
	
	
	
	private Map handle(File file, String text) throws Exception
	{
		File root = (File) findRoot.t(file);
		if(root==null) throw new Exception("Root not found for xhtml file: "+file);
		
		List links = (List) extract1.t(text);
		
		String rootPath = root.getAbsolutePath();
		int rootLen = rootPath.length();
		
		Map map = new HashMap();
		for(int i=0;i<links.size();i++)
		{
			String src = (String) links.get(i);
			File linkedFile = resolveFile(root, file, src);
			String includePath = linkedFile.getAbsolutePath();
			
			if(!includePath.startsWith(rootPath)) throw new Exception("Invalid include filepath: "+includePath);
			String location = includePath.substring(rootLen+1, includePath.length()-6).replace(File.separator,".");
			map.put(location, linkedFile);
		}
		return map;
	}
	
	private File resolveFile(File root, File file, String src) throws Exception
	{return (File) resolveFile.t(new Object[]{root,file,src});}
}