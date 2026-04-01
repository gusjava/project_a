package a.entity.gus06.swing.textcomp.autocomplete.entity.addthrow.invaliddatatype;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160812";}
	
	public static final String LINE = "throw new Exception(\"Invalid data type: \"+obj.getClass().getName());";

	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		int pos = comp.getCaretPosition();
		
		comp.getDocument().insertString(pos,LINE,null);
	}
}
