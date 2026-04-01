package a.entity.gus06.x.clipboard.string;

import java.awt.datatransfer.*;
import java.awt.Toolkit;
import a.framework.*;

public class EntityImpl implements Entity, G, P {

	public String creationDate() {return "20251113";}
	
	public Object g() throws Exception
	{
		try {return c().getData(DataFlavor.stringFlavor);}
		catch (Exception e) {return null;}
	}

	public void p(Object obj) throws Exception
	{copy((String) obj);}

	private Clipboard c()
	{return Toolkit.getDefaultToolkit().getSystemClipboard();}

	private void copy(String s)
	{
		StringSelection t = new StringSelection(s);
		c().setContents(t, t);
	}
}
