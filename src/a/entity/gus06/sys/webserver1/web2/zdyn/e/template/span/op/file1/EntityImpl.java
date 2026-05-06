package a.entity.gus06.sys.webserver1.web2.zdyn.e.template.span.op.file1;

import a.framework.*;
import java.util.Map;
import java.util.Collections;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141024";}

	private Service formatInfo;
	private Service encodeHtml;
	private Service loadTemplate;
	private Service readFile;


	public EntityImpl() throws Exception
	{
		formatInfo = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.span.formatinfo2");
		encodeHtml = Outside.service(this,"gus06.string.transform.format.html.encode");
		loadTemplate = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.template.load");
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map span = (Map) obj;
		
		R mr = (R) span.get("main");
		Object data = formatInfo.t(span);
		String text = findText(mr,data);
		
		P h = (P) mr.r("data h");
		h.p(encodeHtml.t(text));
	}
	
	
	private String findText(R mr, Object data) throws Exception
	{
		if(data instanceof File)
			return (String) readFile.t(data);
		if(data instanceof String)
			return (String) loadTemplate.t(new Object[]{mr,data});
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
}
