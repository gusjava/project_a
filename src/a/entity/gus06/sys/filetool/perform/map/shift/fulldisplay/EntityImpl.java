package a.entity.gus06.sys.filetool.perform.map.shift.fulldisplay;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200410";}
	
	public static final String FULLDISPLAY = "fulldisplay";

	
	public void p(Object obj) throws Exception
	{
		Map prop = (Map) obj;
		boolean isFullDisplay = isBoolDF(prop,FULLDISPLAY);
		prop.put(FULLDISPLAY,""+(!isFullDisplay));
	}
	
	private boolean isBoolDF(Map prop, String key)
	{
		if(!prop.containsKey(key)) return false;
		return prop.get(key).equals("true");
	}
}
