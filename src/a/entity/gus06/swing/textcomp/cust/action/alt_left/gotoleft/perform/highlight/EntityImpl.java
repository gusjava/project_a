package a.entity.gus06.swing.textcomp.cust.action.alt_left.gotoleft.perform.highlight;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20220211";}

	
	
	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	
	
	public boolean f(Object obj) throws Exception
	{return perform((JTextComponent) obj);}
	
	
	
	private boolean perform(JTextComponent comp) throws Exception
	{
		int p = comp.getCaretPosition();
	
		Highlighter high = comp.getHighlighter();
		Highlighter.Highlight[] h = high.getHighlights();
		if(h.length==0) return false;
		
		for(int i=h.length-1;i>=0;i--)
		{
			int start = h[i].getStartOffset();
			int end = h[i].getEndOffset();
			if(start!=end && end<p)
			{
				comp.setCaretPosition(start);
				return true;
			}
		}
		return true;
	}
}