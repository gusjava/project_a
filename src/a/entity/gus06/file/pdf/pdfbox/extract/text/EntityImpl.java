package a.entity.gus06.file.pdf.pdfbox.extract.text;

import java.io.File;
import java.io.StringWriter;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150617";}


	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		StringWriter writer = new StringWriter();

		try(PDDocument doc = Loader.loadPDF(file))
		{
			PDFTextStripper stripper = new PDFTextStripper();
			stripper.writeText(doc,writer);
		}
		return writer.getBuffer().toString();
	}
}
