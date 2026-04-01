package a.entity.gus06.file.pdf.lowagie.pdfpage.number;

import java.io.File;
import java.io.FileInputStream;
import com.lowagie.text.pdf.PdfReader;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150823";}


	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		FileInputStream fis = null;
		PdfReader reader = null;
		Integer nbPages = null;

		try
		{
			fis = new FileInputStream(file);
			reader = new PdfReader(fis);
			nbPages = reader.getNumberOfPages();
		}
		finally
		{
			if(fis!=null) fis.close();
			if(reader!=null) reader.close();
		}
		return nbPages;
	}
}
