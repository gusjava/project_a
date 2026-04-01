package a.entity.gus06.file.editor.main.builder.sync;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140723";}


	private Service newEntity;
	private Service fileToName;
	
	private Map map;
	
	
	public EntityImpl() throws Exception
	{
		newEntity = Outside.service(this,"entitynew");
		fileToName = Outside.service(this,"gus06.file.editor.main.filetoname");
		
		map = new HashMap();
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		String name = fileToName(file);
		if(name==null) return null;
		
		if(!map.containsKey(name))
			map.put(name,build(name));
		
		Object editor = map.get(name);
		((P)editor).p(file);
		return editor;
	}
	
	
	private String fileToName(File file) throws Exception
	{return (String) fileToName.t(file);}
	
	
	private Object build(String name) throws Exception
	{return newEntity.t(name);}
}
