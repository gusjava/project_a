package a.entity.gus06.swing.textcomp.autocomplete.entity.addnano;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150920";}
	
	public static final String TEXT = "System.nanoTime()";
	
	
	public void p( Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String text = comp.getText();
		int pos = comp.getCaretPosition();
		
		comp.getDocument().insertString(pos,TEXT,null);
		comp.setCaretPosition(pos+TEXT.length());
	}
}