package a.entity.gus.y.swingactions1.ctrl_down.select.after.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.Caret;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240120";}

	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	private void perform(JTextComponent comp) throws Exception
	{
		String text = comp.getText();
		Caret caret = comp.getCaret();
		int length = text.length();
		
		int dot = caret.getDot();
		int mark = caret.getMark();
		
		if(dot<length && isLineDelim(text.charAt(dot+1))) dot++;
		if(dot<length) dot++;
		
		while(dot<length && !isLineDelim(text.charAt(dot))) dot++;
		if(dot<mark && isLineDelim(text.charAt(dot))) dot++;
		
		caret.moveDot(dot);
	}
	
	private boolean isLineDelim(char c)
	{return c=='\n' || c=='\r';}
}
