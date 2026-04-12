package a.entity.gus06.framework.doc.en.feature_r_use;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20251130";}
	
	public Object g() throws Exception
	{
		return """
The R feature is typically used to expose class variables or precomputed objects.
Its implementation provides a way to access values by key, allowing other entities or services to retrieve internal data safely and consistently.
The special key "keys" returns a String array of all other available keys, allowing external code to query the entity dynamically.

Example :

public Object r(String key) throws Exception {
    if (key.equals("source")) return source;
    if (key.equals("strokes")) return strokes;
    if (key.equals("scale")) return scale;
    if (key.equals("offset")) return offset;

    if (key.equals("keys")) return new String[]{
        "source","strokes","scale","offset"
    };
    throw new Exception("Unknown key: " + key);
}
""";
	}
}