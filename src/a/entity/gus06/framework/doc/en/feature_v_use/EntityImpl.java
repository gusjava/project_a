package a.entity.gus06.framework.doc.en.feature_v_use;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20251130";}
	
	public Object g() throws Exception
	{
		return """
The V feature is typically used to assign values to an entity’s internal variables.
Its implementation defines a method that receives a key and an associated value, and updates the corresponding internal field accordingly.

Example :

public void v(String key, Object obj) throws Exception
{
	if(key.equals("dataHolder")) {initDataHolder(obj);return;}
	if(key.equals("config")) {initConfig((Map) obj);return;}
	if(key.equals("reset")) {resetComp();return;}
	
	throw new Exception("Unknown key: "+key);
}
""";
	}
}