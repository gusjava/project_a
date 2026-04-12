package a.entity.gus06.y.maven1.html.xml;

import a.framework.*;

import java.net.URL;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251221";}

	private Service urlToText;
	private Service xmlParser;

	public EntityImpl() throws Exception
	{
		urlToText = Outside.service(this,"gus06.web.download.urltotext");
		xmlParser = Outside.service(this,"gus06.file.convert.xml.parser");
	}

	public Object t(Object obj) throws Exception
	{
		String url = (String) obj;
		String xml = (String) urlToText.t(url);
		
		return xmlParser.t(xml);
	}
}
