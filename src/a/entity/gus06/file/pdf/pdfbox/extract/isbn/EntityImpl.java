package a.entity.gus06.file.pdf.pdfbox.extract.isbn;

import java.io.File;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201105";}
	
	private Service isbnExtractor;

	public EntityImpl() throws Exception
	{isbnExtractor = Outside.service(this,"gus06.string.transform.regexp.extract.isbn");}


	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;

		try(PDDocument doc = Loader.loadPDF(file))
		{
			PDFTextStripper stripper = new PDFTextStripper();
			int number = doc.getNumberOfPages();
			int index = 1;
			while(index <= number)
			{
				stripper.setStartPage(index);
				stripper.setEndPage(index+1);
				
				String text = stripper.getText(doc);
				String isbn = extractFromText(text);
				if(isbn!=null) return isbn;
				index++;
			}
		}
		return null;
	}
	
	private String extractFromText(String text) throws Exception
	{return (String) isbnExtractor.t(text);}
}