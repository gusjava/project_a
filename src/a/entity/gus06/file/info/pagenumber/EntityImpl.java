package a.entity.gus06.file.info.pagenumber;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150825";}


	private Service checkPdf;
	private Service infoPdf;
	
	
	public EntityImpl() throws Exception
	{
		checkPdf = Outside.service(this,"gus06.file.pdf.check");
		infoPdf = Outside.service(this,"gus06.file.pdf.lowagie.pdfpage.number");
	}



	public Object t(Object obj) throws Exception
	{
		if(checkPdf.f(obj)) return infoPdf.t(obj);
		
		// word
		// excel
		// ps
		// ...
		return null;
	}
}
