package a.entity.gus06.beep.multibeep;

import java.awt.Toolkit;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201216";}

	public void p(Object obj) throws Exception
	{
		int nb = toInt(obj);
		for(int i=0;i<nb;i++)
		{
			Toolkit.getDefaultToolkit().beep();
			Thread.sleep(1000);
		}
	}
	
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
}