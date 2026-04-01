package a.entity.gus06.file.pdf.isbn;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251123";}

	private Service fromContent;

	public EntityImpl() throws Exception
	{
		fromContent = Outside.service(this,"gus06.file.pdf.pdfbox.extract.isbn");
	}
	
	public Object t(Object obj) throws Exception
	{
		String isbn1 = (String) fromContent.t(obj);
		if(isbn1!=null) return isbn1;
		
		return null;
	}
}
