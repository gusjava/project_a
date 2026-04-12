package a.entity.gus06.y.maven1.html.pom;

import a.framework.*;

import java.net.URL;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251221";}

	private Service urlToText;
	private Service xmlToPom;

	public EntityImpl() throws Exception
	{
		urlToText = Outside.service(this,"gus06.web.download.urltotext");
		xmlToPom = Outside.service(this,"gus06.y.maven1.xmltopom");
	}

	public Object t(Object obj) throws Exception
	{
		String url = (String) obj;
		String xml = (String) urlToText.t(url);
		return xmlToPom.t(xml);
	}
}
