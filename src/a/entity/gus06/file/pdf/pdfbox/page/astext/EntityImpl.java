package a.entity.gus06.file.pdf.pdfbox.page.astext;

import java.io.File;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191111";}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		int index = ((Integer) o[1]).intValue();
		
		try(PDDocument doc = Loader.loadPDF(file))
		{
			PDFTextStripper stripper = new PDFTextStripper();
			int number = pageNumber(doc);

			stripper.setStartPage(index+1);
			stripper.setEndPage(index+1);
			return stripper.getText(doc).trim();
		}
	}
	
	private int pageNumber(PDDocument doc)
	{
		try{return doc.getNumberOfPages();}
		catch(NullPointerException e) {return -1;}
	}
}
