package a.entity.gus06.swing.textcomp.cust.action.ctrl_up.select.before.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.Caret;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160512";}

	
	
	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		String text = comp.getText();
		Caret caret = comp.getCaret();
		
		int dot = caret.getDot();
		int mark = caret.getMark();
		
		if(dot>0 && isLineDelim(text.charAt(dot-1))) dot--;
		if(dot>0) dot--;
		
		while(dot>0 && !isLineDelim(text.charAt(dot))) dot--;
		if(dot>0 && dot<mark && isLineDelim(text.charAt(dot))) dot++;
		
		caret.moveDot(dot);
	}
	
	
	private boolean isLineDelim(char c)
	{return c=='\n' || c=='\r';}
}
