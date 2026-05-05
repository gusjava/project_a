package a.entity.gus06.file.pdf.pdfbox.extract.text.pages;

import java.io.File;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import a.framework.*;
import java.io.IOException;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150617";}
	
	public static final String PAGE_DELIM = "________________\n";
	
	private Service isLocked;
	
	public EntityImpl() throws Exception
	{
		isLocked = Outside.service(this,"gus.x.file.filter.islocked");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(isLocked.f(file)) throw new Exception("File is locked before PDDocument extractText: "+file);
		String text = extractText(file);
		if(isLocked.f(file)) throw new Exception("File is locked after PDDocument extractText: "+file);
		return text;
	}
	
	private String extractText(File file) throws IOException
	{
		int index = 1;
		StringBuffer b = new StringBuffer();
		try(PDDocument doc = Loader.loadPDF(file))
		{
			PDFTextStripper stripper = new PDFTextStripper();
			int number = doc.getNumberOfPages();

			while(index <= number)
			{
				stripper.setStartPage(index);
				stripper.setEndPage(index);
				
				String text = stripper.getText(doc).trim();
				
				if(!text.equals(""))
				{
					b.append("PAGE"+index+PAGE_DELIM);
					b.append(text+"\n");
				}
				index++;
			}
		}
		return b.toString();
	}
}
