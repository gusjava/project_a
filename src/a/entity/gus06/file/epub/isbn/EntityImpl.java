package a.entity.gus06.file.epub.isbn;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251123";}

	private Service fromProp;
	private Service fromContent;

	public EntityImpl() throws Exception
	{
		fromProp = Outside.service(this,"gus06.file.epub.properties.isbn");
		fromContent = Outside.service(this,"gus06.file.epub.extract.fulltext.isbn");
	}
	
	public Object t(Object obj) throws Exception
	{
		String isbn1 = (String) fromContent.t(obj);
		if(isbn1!=null) return isbn1;
		
		String isbn2 = (String) fromProp.t(obj);
		if(isbn2!=null) return isbn2;
		
		return null;
	}
}
