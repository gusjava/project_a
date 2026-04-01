package a.entity.gus06.sys.filemanagement1.tool.ebook.format.description;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201105";}



	private Service normalizeFileName;
	private Service decodeHtml;
	private Service removeTags;
	
	public EntityImpl() throws Exception
	{
		normalizeFileName = Outside.service(this,"gus06.string.transform.normalize.filename");
		decodeHtml = Outside.service(this,"gus06.string.transform.format.html.decode");
		removeTags = Outside.service(this,"gus06.string.html.tag.remove");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return format((String) obj);
	}
	
	
	private String format(String value) throws Exception
	{
		value = value.trim().replaceAll("[\n\t ]+"," ");
		value = (String) decodeHtml.t(value);
		value = value.replace("<br/>","\n").replace("<br>","\n");
		return (String) removeTags.t(value);
	}
}
