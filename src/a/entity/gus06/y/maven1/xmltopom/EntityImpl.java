package a.entity.gus06.y.maven1.xmltopom;

import a.framework.*;

import java.net.URL;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251230";}

	private Service xmlParser;
	private Service formatData;

	public EntityImpl() throws Exception
	{
		xmlParser = Outside.service(this,"gus06.file.convert.xml.parser");
		formatData = Outside.service(this,"gus06.y.maven1.formatdata");
	}

	public Object t(Object obj) throws Exception
	{
		String xml = (String) obj;
		if(xml==null || xml.equals("")) return null;
		
		try
		{
			Map data = (Map) xmlParser.t(xml);
			return formatData.t(data);
		}
		catch(Exception e)
		{
			throw new Exception("Failed to extract maven data from xml: "+xml, e);
		}
	}
}
