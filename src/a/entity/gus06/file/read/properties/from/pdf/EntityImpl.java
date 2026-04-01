package a.entity.gus06.file.read.properties.from.pdf;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151018";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.pdf.pdfbox.extract.properties");
	}
	
	public Object t(Object obj) throws Exception
	{
		return perform.t(obj);
	}
}
