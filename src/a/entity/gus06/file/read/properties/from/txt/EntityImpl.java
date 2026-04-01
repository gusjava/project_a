package a.entity.gus06.file.read.properties.from.txt;

import a.framework.*;
import java.util.Properties;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151018";}


	private Service read;
	private Service stringToMap;

	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus06.file.read.string.autodetect");
		stringToMap = Outside.service(this,"gus06.convert.stringtomap");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String text = (String) read.t(obj);
		return stringToMap.t(text);
	}
}
