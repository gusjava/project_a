package a.entity.gus06.file.pdf.pdfbox.extract.properties;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191010";}

	public static final String AUTHOR = "AUTHOR";
	public static final String CREATIONDATE = "CREATIONDATE";
	public static final String CREATOR = "CREATOR";
	public static final String MODIFICATIONDATE = "MODIFICATIONDATE";
	public static final String PRODUCER = "PRODUCER";
	public static final String SUBJECT = "SUBJECT";
	public static final String TITLE = "TITLE";
	public static final String TRAPPED = "TRAPPED";

	

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		Map m = new HashMap();
		
		try(PDDocument doc = Loader.loadPDF(file))
		{
			PDDocumentInformation pdfInfo = doc.getDocumentInformation();
			
			String author = pdfInfo.getAuthor();
			String creationDate = toString(getCreationDate(pdfInfo));
			String modificationDate = toString(getModificationDate(pdfInfo));
			String creator = pdfInfo.getCreator();
			String producer = pdfInfo.getProducer();
			String subject = pdfInfo.getSubject();
			String title = pdfInfo.getTitle();
			String trapped = pdfInfo.getTrapped();
			
			put(m,AUTHOR,author);
			put(m,CREATOR,creator);
			put(m,CREATIONDATE,creationDate);
			put(m,MODIFICATIONDATE,modificationDate);
			put(m,PRODUCER,producer);
			put(m,SUBJECT,subject);
			put(m,TITLE,title);
			put(m,TRAPPED,trapped);
		}
		return m;
	}
	
	
	
	private void put(Map m, String key, String value)
	{if(value!=null) m.put(key,value);}
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	private String toString(Calendar c)
	{
		if(c==null) return null;
		return sdf.format(c.getTime());
	}
	
	private Calendar getCreationDate(PDDocumentInformation pdfInfo)
	{
		try{return pdfInfo.getCreationDate();}
		catch(Exception e)
		{Outside.err(this,"getCreationDate(PDDocumentInformation)",e);}
		return null;
	}
	
	private Calendar getModificationDate(PDDocumentInformation pdfInfo)
	{
		try{return pdfInfo.getModificationDate();}
		catch(Exception e)
		{Outside.err(this,"getModificationDate(PDDocumentInformation)",e);}
		return null;
	}
}