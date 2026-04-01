package a.entity.gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.focus.caret.keepword;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151108";}


	private Service handleRange;
	
	public EntityImpl() throws Exception
	{
		handleRange = Outside.service(this,"gus06.swing.textcomp.textfocus.handlerange");
	}
	
		
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String text = comp.getText();
		
		int length = text.length();
		int pos = comp.getCaretPosition();
		int start = pos-1;
		int end = pos;
		
		while(start>=0 && !isWordDelim(text.charAt(start))) start--;	
		while(end<length && !isWordDelim(text.charAt(end))) end++;
		start++;
		
		int[] range = new int[]{start,end};
		handleRange.p(new Object[]{comp,range});
	}
	
	
	private boolean isWordDelim(char c)
	{return " ,;:.!?{}()[]/\\\t\n\r<>=-+'\"".contains(""+c);}
}
