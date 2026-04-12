package a.entity.gus06.framework.doc.en.feature_e_use;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20251130";}
	
	public Object g() throws Exception
	{
		return """
The E feature is typically used for executing tasks that do not require input or produce output.
Entities implementing E perform actions when invoked, but do not receive parameters nor return results.

Entity Example :

package a.entity.gus.beep;

import java.awt.Toolkit;
import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140701";}

	public void e() throws Exception
	{Toolkit.getDefaultToolkit().beep();}
}
""";
	}
}