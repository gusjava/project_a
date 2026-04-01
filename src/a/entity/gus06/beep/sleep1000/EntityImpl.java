package a.entity.gus06.beep.sleep1000;

import java.awt.Toolkit;
import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20150909";}

	public void e() throws Exception
	{
		Toolkit.getDefaultToolkit().beep();
		Thread.sleep(1000);
	}
}
