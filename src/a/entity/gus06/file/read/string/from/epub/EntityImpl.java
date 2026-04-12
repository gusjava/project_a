package a.entity.gus06.file.read.string.from.epub;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251123";}

	private Service extractText;

	public EntityImpl() throws Exception
	{
		extractText = Outside.service(this,"gus06.file.epub.extract.fulltext");
	}
	
	public Object t(Object obj) throws Exception
	{
		return extractText.t(obj);
	}
}