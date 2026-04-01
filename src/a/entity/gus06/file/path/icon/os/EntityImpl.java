package a.entity.gus06.file.path.icon.os;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220624";}

	private Service nameToIcon;

	public EntityImpl() throws Exception
	{nameToIcon = Outside.service(this,"gus06.file.name.icon.os");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String name = pathToName((String) obj);
		return nameToIcon.t(name);
	}
	
	
	private String pathToName(String path)
	{
		String[] n = path.split("[\\\\/]");
		if(n.length==0) return null;
		return n[n.length-1];
	}
}