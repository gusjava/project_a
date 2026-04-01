package a.entity.gus06.file.word.poi.docx.extract.text;

import a.framework.*;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160711";}

	
	
	public Object t(Object obj) throws Exception
	{
    		File file = (File) obj;
	    	FileInputStream fis = new FileInputStream(file);
	        
	        XWPFDocument document = new XWPFDocument(fis);
		XWPFWordExtractor extractor = new XWPFWordExtractor(document);
        	String text = extractor.getText();
        	extractor.close();
        	document.close();

	        return text;
	}
}