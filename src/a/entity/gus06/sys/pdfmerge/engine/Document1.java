package a.entity.gus06.sys.pdfmerge.engine;

import java.io.File;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

public class Document1 {
	
	private FileOutputStream fos;
	private Document document;
	private PdfWriter writer;
	
	private Object previous;
	
	public Document1(File file) throws Exception
	{
		fos = new FileOutputStream(file);
	}
	
	public void importPage(PdfReader pdfReader, int index) throws Exception
	{
		Rectangle pageSize = pdfReader.getPageSize(index);
		
		if(previous==null)
			initDocument(pageSize);
		else
		{
			document.setPageSize(pageSize);
			addPrevious();
		}
		previous = writer.getImportedPage(pdfReader,index);
	}
	
	public void addImagePage(File file) throws Exception
	{
		Image im = Image.getInstance(file.getAbsolutePath());
		Rectangle pageSize = new Rectangle(im.getPlainWidth(),im.getPlainHeight());
		
		if(previous==null)
			initDocument(pageSize);
		else
		{
			document.setPageSize(pageSize);
			addPrevious();
		}
		
		previous = im;
	}
	
	private void initDocument(Rectangle pageSize) throws Exception
	{
		document = new Document(pageSize,0,0,0,0);
		writer = PdfWriter.getInstance(document,fos);
		document.open();
	}
	
	private void addPrevious() throws Exception
	{
		if(previous==null) return;
		document.newPage();
		
		if(previous instanceof Image)
			document.add((Image)previous);
		else if(previous instanceof PdfImportedPage)
			writer.getDirectContent().addTemplate((PdfImportedPage)previous,0,0);
	}
	
	public void close() throws Exception
	{
		if(document==null) return;
		addPrevious();
		
		document.close();
		fos.close();
		writer.close();
		
		document = null;
		fos = null;
		writer = null;
		previous = null;
	}
}
