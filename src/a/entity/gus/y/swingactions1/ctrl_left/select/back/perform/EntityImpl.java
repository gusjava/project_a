package a.entity.gus.y.swingactions1.ctrl_left.select.back.perform;

import a.framework.*;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240120";}

	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	private void perform(JTextComponent comp) throws Exception
	{
		int pos = comp.getCaretPosition();
		String text = comp.getText();
		int length = text.length();
		
		int start = pos;
		int end = pos;
		
		if(start==length) start--;
		
		if(start>0  && isLineDelim(text.charAt(start))) start--;
		while(start>0 && !isLineDelim(text.charAt(start))) start--;
		if(start>0 && isLineDelim(text.charAt(start))) start++;
		
		int start0 = comp.getSelectionStart();
		int end0 = comp.getSelectionEnd();
		if(start==start0 && end==end0) start = 0; 
		
		comp.select(start,end);
	}
	
	private boolean isLineDelim(char c)
	{return c=='\n' || c=='\r';}
}
