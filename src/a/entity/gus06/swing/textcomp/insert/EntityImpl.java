package a.entity.gus06.swing.textcomp.insert;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191201";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		String text = (String) o[1];
		
		int pos = comp.getCaretPosition();
		int delta = text.length()-1;
		comp.getDocument().insertString(pos,text,null);
		comp.setCaretPosition(pos+delta);
	}
}
