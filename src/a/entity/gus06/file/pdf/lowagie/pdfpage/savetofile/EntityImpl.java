package a.entity.gus06.file.pdf.lowagie.pdfpage.savetofile;

import a.framework.*;
import java.io.File;
import java.io.FileOutputStream;
import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150619";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		PdfReader reader = (PdfReader) o[0];
		File file = (File) o[1];
		int index = ((Integer) o[2]).intValue();
		
		file.getParentFile().mkdirs();
		
		FileOutputStream fos = new FileOutputStream(file);
		Rectangle pageSize = reader.getPageSize(index);
		Document document = new Document(pageSize,0,0,0,0);
		PdfWriter writer = PdfWriter.getInstance(document,fos);
		document.open();
		
		PdfImportedPage page = writer.getImportedPage(reader,index);
		document.newPage();
		writer.getDirectContent().addTemplate(page,0,0);
		
		document.close();
		fos.close();
		writer.close();
	}
}
