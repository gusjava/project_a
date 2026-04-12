package a.entity.gus06.file.pdf.pdfbox.cover.asimage;

import java.awt.image.BufferedImage;
import java.io.File;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150616";}

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		try(PDDocument doc = Loader.loadPDF(file))
		{
			PDFRenderer renderer = new PDFRenderer(doc);
			BufferedImage firstPageImage = renderer.renderImageWithDPI(0, 200);
			return firstPageImage;
		}
	}
}
