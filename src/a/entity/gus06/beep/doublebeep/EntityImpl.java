package a.entity.gus06.beep.doublebeep;

import java.awt.Toolkit;
import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140808";}

	public void e() throws Exception
	{
		for(int i=0;i<2;i++)
		{
			Toolkit.getDefaultToolkit().beep();
			Thread.sleep(1000);
		}
	}
}
