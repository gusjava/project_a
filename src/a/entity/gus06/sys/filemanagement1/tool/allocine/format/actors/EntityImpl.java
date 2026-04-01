package a.entity.gus06.sys.filemanagement1.tool.allocine.format.actors;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201108";}



	private Service normalizeFileName;
	
	public EntityImpl() throws Exception
	{
		normalizeFileName = Outside.service(this,"gus06.string.transform.normalize.filename");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return format((String) obj);
	}
	
	
	private String format(String value) throws Exception
	{
		value = value.trim()
			.replaceAll("[\n\t ]+"," ")
			.replaceAll(" *, *",", ")
			.replaceAll(" *; *",", ");
		
		return (String) normalizeFileName.t(value);
	}
}