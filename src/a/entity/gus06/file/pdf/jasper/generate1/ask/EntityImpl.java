package a.entity.gus06.file.pdf.jasper.generate1.ask;

import a.framework.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221019";}
	
	public static final String AUTHOR = "AUTHOR";
	public static final String EXT = "pdf";


	private Service generate;
	private Service getTmpFile;
	private Service inputTextArea;
	private Service mapSplit;

	public EntityImpl() throws Exception
	{
		generate = Outside.service(this,"gus06.file.pdf.jasper.generate1");
		getTmpFile = Outside.service(this,"gus06.file.tmpfile");
		inputTextArea = Outside.service(this,"gus06.input.textarea.dialog");
		mapSplit = Outside.service(this,"gus06.data.perform.msplit_tn");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File jrxmlFile = (File) obj;
		File pdfFile = (File) getTmpFile.t(EXT);
		Map param = askForParams();
		
		generate.p(new Object[]{jrxmlFile, pdfFile, param, null, AUTHOR});
		return pdfFile;
	}
	
	
	private Map askForParams() throws Exception
	{
		String input = (String) inputTextArea.g();
		if(input==null) return new HashMap();
		return (Map) mapSplit.t(input);
	}
}
