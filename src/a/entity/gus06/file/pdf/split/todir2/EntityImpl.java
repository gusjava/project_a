package a.entity.gus06.file.pdf.split.todir2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import a.framework.*;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20171004";}
	
	public static final String TAG_INDEX = "<index>";


	private Service splitter;

	public EntityImpl() throws Exception
	{splitter = Outside.service(this,"gus06.file.pdf.lowagie.pdfpage.splitter");}

	
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File pdfFile = o[0];
		File outputDir = o[1];
		
		if(!pdfFile.getName().toLowerCase().endsWith(".pdf"))
			throw new Exception("PDF file expected: "+pdfFile);
		
		String name = "page"+TAG_INDEX+".pdf";
		String rule = outputDir+File.separator+name;
		return splitter.t(new Object[]{pdfFile,rule});
	}
}
