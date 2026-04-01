package a.entity.gus06.framework.doc.en.feature_h_use;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20251130";}
	
	public Object g() throws Exception
	{
		return """
The H feature is used for entities that model a mathematical function from ℝ to ℝ.

Entity Example :

package gus06.entity.gus.math.function.h.sigmoid;

import a.framework.*;

public class EntityImpl implements Entity, H {

	public String creationDate() {return "20150309";}
	
	public double h(double value) throws Exception
	{return 1 / (1 + Math.exp(-value));}
}""";
	}
}