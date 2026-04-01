package a.entity.gus06.string.parser.builder0;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20140818";}
	

	private Service buildParser;
	private Service findMap;
	
	public EntityImpl() throws Exception
	{
		buildParser = Outside.service(this,"gus06.string.parser.builder");
		findMap = Outside.service(this,"gus06.app.inside.parser");
	}
	
	
	public Object t(Object obj) throws Exception
	{return r((String) obj);}
	
	
	
	public Object r(String key) throws Exception
	{
		Map map = (Map) findMap.r(key);
		return buildParser.t(map);
	}	
}
