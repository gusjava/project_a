package a.entity.gus06.sys.expression1.apply.op._read_html;

import a.framework.*;
import java.io.File;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151129";}


	private Service htmlParser;
	private Service readFile;
	private Service urlToText;
	
	public EntityImpl() throws Exception
	{
		htmlParser = Outside.service(this,"gus06.file.convert.html.parser");
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		urlToText = Outside.service(this,"gus06.web.download.urltotext.utf8");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return stringToData((String) obj);
		if(obj instanceof File) return fileToData((File) obj);
		if(obj instanceof URL) return urlToData((URL) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private Object urlToData(URL url) throws Exception
	{
		String s = (String) urlToText.t(url);
		return htmlParser.t(s);
	}
	
	private Object fileToData(File file) throws Exception
	{
		String s = (String) readFile.t(file);
		return htmlParser.t(s);
	}
	
	private Object stringToData(String s) throws Exception
	{
		return htmlParser.t(s);
	}
}
