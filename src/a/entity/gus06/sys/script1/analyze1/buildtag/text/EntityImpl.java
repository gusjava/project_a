package a.entity.gus06.sys.script1.analyze1.buildtag.text;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150829";}
	
	
	public static final String T_TEXT = "text";
	public static final String K_VALUE = "value";
	
	
	
	private Service newMap;
	
	public EntityImpl() throws Exception
	{newMap = Outside.service(this,"gus06.sys.script1.analyze1.buildtag.newmap");}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		Map tag = (Map) newMap.t(T_TEXT);
		tag.put(K_VALUE,s);
		
		return tag;
	}
}