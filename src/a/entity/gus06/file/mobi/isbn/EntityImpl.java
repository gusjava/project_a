package a.entity.gus06.file.mobi.isbn;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251123";}

	private Service fromProp;

	public EntityImpl() throws Exception
	{
		fromProp = Outside.service(this,"gus06.file.mobi.properties.isbn");
	}
	
	public Object t(Object obj) throws Exception
	{
		String isbn1 = (String) fromProp.t(obj);
		if(isbn1!=null) return isbn1;
		
		return null;
	}
}
