package a.entity.gus06.file.pdf.ocr.extract.text.pages;

import java.io.File;
import java.awt.image.BufferedImage;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210607";}
	
	public static final String PAGE_DELIM = "________________\n";


	private Service imageToText;
	private Service isLocked;
	
	public EntityImpl() throws Exception
	{
		imageToText = Outside.service(this,"gus06.sys.tesseract1.imagetotext");
		isLocked = Outside.service(this,"gus06.file.filter.islocked");
	}

	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj; 
		if(isLocked.f(file)) throw new Exception("File is locked before PDDocument extractTextOCR: "+file);
		String text = extractTextOCR(file);
		if(isLocked.f(file)) throw new Exception("File is locked after PDDocument extractTextOCR: "+file);
		return text;
	}
	
	private String extractTextOCR(File file) throws Exception
	{
		StringBuffer b = new StringBuffer();
		try(PDDocument doc = Loader.loadPDF(file))
		{
			int number = doc.getNumberOfPages();
			if(number==0) throw new Exception("No page found");
			PDFRenderer renderer = new PDFRenderer(doc);
			
			for(int i=0;i<number;i++)
			{
				BufferedImage img = renderer.renderImageWithDPI(i, 200);
				String text = (String) imageToText.t(img);
				if(text==null) text = "";
				
				b.append("PAGE"+(i+1)+PAGE_DELIM);
				b.append(text);
			}
		}
		return b.toString();
	}
}
