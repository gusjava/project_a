package a.entity.gus06.file.pdf.pdfbox.page.asimage;

import java.awt.image.BufferedImage;
import java.io.File;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191116";}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		int index = ((Integer) o[1]).intValue();
		
		try(PDDocument doc = Loader.loadPDF(file))
		{
			PDFRenderer renderer = new PDFRenderer(doc);
			BufferedImage firstPageImage = renderer.renderImageWithDPI(index, 200);
			return firstPageImage;
		}
	}
}
