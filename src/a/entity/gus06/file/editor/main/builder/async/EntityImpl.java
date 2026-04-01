package a.entity.gus06.file.editor.main.builder.async;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141207";}


	private Service build;
	private Service fileToName;
	
	private Map map;
	
	
	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.sys.async.guibuilder.dataholder");
		fileToName = Outside.service(this,"gus06.file.editor.main.filetoname");
		
		map = new HashMap();
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			
			File file = (File) o[0];
			String name = (String) o[1];
			return fileToEditor(file, name);
		}
		if(obj instanceof File)
		{
			File file = (File) obj;
			return fileToEditor(file);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private Object fileToEditor(File file) throws Exception
	{
		String name = fileToName(file);
		return fileToEditor(file, name);
	}
	
	private Object fileToEditor(File file, String name) throws Exception
	{
		if(name==null) return null;
		
		if(!map.containsKey(name))
		{
			Object editor = build(name,file);
			map.put(name,editor);
			return editor;
		}
		
		Object editor = map.get(name);
		((P)editor).p(file);
		return editor;
	}
	
	
	
	private String fileToName(File file) throws Exception
	{return (String) fileToName.t(file);}
	
	
	private Object build(String name, Object data) throws Exception
	{return build.t(new Object[]{name,data});}
}