package a.entity.gus06.framework.doc.en.feature_t_use;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20251130";}
	
	public Object g() throws Exception
	{
		return """
The T feature is typically used for stateless transformation entities, such as data formatting, conversion, 
or any operation that transforms input into output without maintaining internal state.

Entity Example :

package gus06.entity.gus.string.transform.character.keep.first;

import a.framework.*;

public class EntityImpl implements Entity, T {

    public String creationDate() { return "20150926"; }

    public Object t(Object obj) throws Exception {
        String s = (String) obj;
        if (s.equals("")) return "";
        return s.substring(0, 1);
    }
}
""";
	}
}