package a.entity.gus06.file.read.string.from.pdf;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150617";}

	private Service extractText;
	private Service extractTextOCR;

	public EntityImpl() throws Exception
	{
		extractText = Outside.service(this,"gus06.file.pdf.pdfbox.extract.text.pages");
		extractTextOCR = Outside.service(this,"gus06.file.pdf.ocr.extract.text.pages");
	}
	
	public Object t(Object obj) throws Exception
	{
		String text = (String) extractText.t(obj);
		if(text!=null && !text.equals("")) return text;
		
		return extractTextOCR.t(obj);
	}
}