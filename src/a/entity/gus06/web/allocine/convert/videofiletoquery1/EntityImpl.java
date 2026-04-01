package a.entity.gus06.web.allocine.convert.videofiletoquery1;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200112";}


	private Service getName0;

	public EntityImpl() throws Exception
	{
		getName0 = Outside.service(this,"gus06.file.getname0");
	}
	
	public Object t(Object obj) throws Exception
	{
		String name = toName(obj);
		return format(name);
	}
	
	
	private String toName(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof File) return (String) getName0.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String format(String name)
	{
		if(name.contains(" - ")) name = name.split(" - ",2)[1];
		
		name = name.replace("."," ").replace("-"," ").replace("_"," ");
		name = name.replaceAll("\\([^\\)]*\\)"," ");
		
		while(name.contains("  ")) name = name.replace("  "," ");
		return name.trim();
	}
}
