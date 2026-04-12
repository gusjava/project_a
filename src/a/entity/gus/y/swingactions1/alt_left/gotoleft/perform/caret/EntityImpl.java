package a.entity.gus.y.swingactions1.alt_left.gotoleft.perform.caret;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20240121";}

	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	public boolean f(Object obj) throws Exception
	{return perform((JTextComponent) obj);}
	
	private boolean perform(JTextComponent comp) throws Exception
	{
		int p = comp.getCaretPosition();
		
		PlainDocument document = (PlainDocument) comp.getDocument();
		Element element = document.getParagraphElement(p);
		
		int start = element.getStartOffset();
		if(p>start)
		{
			comp.setCaretPosition(start);
			return true;
		}
		if(p>0)
		{
			comp.setCaretPosition(0);
			return true;
		}
		return false;
	}
}