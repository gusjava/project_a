package a.entity.gus06.framework.doc.en.feature_s_use;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20251130";}
	
	public Object g() throws Exception
	{
		return """
The S feature is used for entities that need to send events to listeners that have been registered to them.
Entities implementing S typically extend the S1 class which provides a send method used to notify all registered listeners:
send(Object source, String id)

For clarity and consistency, the send call is placed inside a dedicated private method with no parameters, and the event identifier (id) matches the method’s own signature.

Example :

private void focusChanged()
{send(this,"focusChanged()");}
""";
	}
}