package a.entity.gus06.swing.textcomp.autocomplete.entity.addthrow.unknownkey;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160812";}
	
	public static final String LINE = "throw new Exception(\"Unknown key: \"+key);";

	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		int pos = comp.getCaretPosition();
		
		comp.getDocument().insertString(pos,LINE,null);
	}
}
