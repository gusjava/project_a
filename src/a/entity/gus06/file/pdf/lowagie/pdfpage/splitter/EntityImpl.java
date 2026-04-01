package a.entity.gus06.file.pdf.lowagie.pdfpage.splitter;

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

	public String creationDate() {return "20150620";}
	
	public static final String TAG_INDEX = "<index>";


	private Service saveToFile;

	public EntityImpl() throws Exception
	{saveToFile = Outside.service(this,"gus06.file.pdf.lowagie.pdfpage.savetofile");}

	

	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File pdfFile = (File) o[0];
		String rule = (String) o[1];
		
		
		FileInputStream is = new FileInputStream(pdfFile);
		PdfReader reader = new PdfReader(is);
		int number = reader.getNumberOfPages();
		
		File[] f = new File[number];
		int l = (""+number).length();
		
		for(int i=0;i<number;i++)
		{
			int index = i+1;
			String path = rule.replace(TAG_INDEX,format(index,l));
			f[i] = new File(path);
			
			Object[] data = new Object[]{reader,f[i],Integer.valueOf(index)};
			saveToFile.p(data);
		}
		is.close();
		return f;
	}
	
	
	private String format(int i, int length)
	{
		String s = ""+i;
		while(s.length()<length) s = "0"+s;
		return s;
	}
}
