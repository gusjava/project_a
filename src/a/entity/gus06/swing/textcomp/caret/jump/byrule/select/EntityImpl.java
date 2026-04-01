package a.entity.gus06.swing.textcomp.caret.jump.byrule.select;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220521";}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];;
		String rule = ""+o[1];
		
		//TODO : prendre en compte les mots cl�s : 
		
		//word, 
		//line, 
		//fromTextStart, 
		//toTextEnd,
		//fromLineStart, 
		//toLineEnd,
		
		int selectionLength = int_(rule);
		int pos = comp.getCaretPosition();
		comp.getCaret().moveDot(pos + selectionLength);
	}
	
	private int int_(String s)
	{return Integer.parseInt(s);}
}