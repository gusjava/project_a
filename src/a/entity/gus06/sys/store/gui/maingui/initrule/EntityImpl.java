package a.entity.gus06.sys.store.gui.maingui.initrule;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140907";}
	
	public static final String KEY = "store.maingui";
	public static final String DEFAULTVALUE = "obj:maingui";

	private Map prop;

	public EntityImpl() throws Exception
	{prop = (Map) Outside.resource(this,"prop");}
	
	
	public Object g() throws Exception
	{
		if(prop.containsKey(KEY))
			return prop.get(KEY);
		return DEFAULTVALUE;
	}
}
