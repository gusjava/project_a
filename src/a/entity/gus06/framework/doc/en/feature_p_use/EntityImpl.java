package a.entity.gus06.framework.doc.en.feature_p_use;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20251130";}
	
	public Object g() throws Exception
	{
		return """
The P feature is typically used by entities that need to receive an object either to store it internally or to apply a specific processing step to it.

Entity Example :

package gus06.entity.gus.clipboard.access.string;

import a.framework.*;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class EntityImpl implements Entity, P, G {

	public String creationDate() {return "20140801";}
	
	public Object g() throws Exception
	{
		try{return c().getData(DataFlavor.stringFlavor);}
		catch(Exception e) {return null;}
	}
	
	public void p(Object obj) throws Exception
	{copy((String) obj);}
	
	private Clipboard c()
	{return Toolkit.getDefaultToolkit().getSystemClipboard();}
	
	private void copy(String s)
	{
		StringSelection t = new StringSelection(s);
		c().setContents(t,t);
	}
}""";
	}
}