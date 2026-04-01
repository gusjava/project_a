package a.entity.gus06.swing.textcomp.cust.action.alt_right.gotoright.perform.caret;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20160903";}

	
	
	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	
	
	public boolean f(Object obj) throws Exception
	{return perform((JTextComponent) obj);}
	
	
	
	private boolean perform(JTextComponent comp) throws Exception
	{
		int p = comp.getCaretPosition();
		int len = comp.getText().length();
		
		PlainDocument document = (PlainDocument) comp.getDocument();
		Element element = document.getParagraphElement(p);
		
		int end = element.getEndOffset();
		if(p<end-1)
		{
			comp.setCaretPosition(end-1);
			return true;
		}
		if(p<len)
		{
			comp.setCaretPosition(len);
			return true;
		}
		return false;
	}
}