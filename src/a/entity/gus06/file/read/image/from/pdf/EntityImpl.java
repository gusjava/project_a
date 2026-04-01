package a.entity.gus06.file.read.image.from.pdf;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150616";}


	private Service imageFromPdf1;
	private Service imageFromPdf2;


	public EntityImpl() throws Exception
	{
		imageFromPdf1 = Outside.service(this,"gus06.file.pdf.pdfbox.cover.asimage");
		imageFromPdf2 = Outside.service(this,"gus06.file.pdf.jpedal.cover.asimage");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		try{return imageFromPdf1.t(obj);}
		catch(Exception e)
		{Outside.err(this,"t(Object)",e);}
		
		try{return imageFromPdf2.t(obj);}
		catch(Exception e)
		{throw new Exception("Failed to read image from pdf: "+obj,e);}
	}
}