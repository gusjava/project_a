package a.entity.gus06.beep;

import java.awt.Toolkit;
import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140701";}

	public void e() throws Exception
	{Toolkit.getDefaultToolkit().beep();}
}
