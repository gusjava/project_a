package a.entity.gus06.framework.doc.en.feature_f_use;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20251130";}
	
	public Object g() throws Exception
	{
		return """
The F feature is typically used for stateless entities that perform filtering or boolean tests.
It receives an object as input and returns a boolean value, indicating whether the input satisfies a condition or passes a filter.

Entity Example :

package gus06.entity.gus.filter.string.is.lower;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160729";}
	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.matches("[a-z]+");
	}
}
""";
	}
}