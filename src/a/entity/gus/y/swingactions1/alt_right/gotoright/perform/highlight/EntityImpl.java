package a.entity.gus.y.swingactions1.alt_right.gotoright.perform.highlight;

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
		
		Highlighter high = comp.getHighlighter();
		Highlighter.Highlight[] h = high.getHighlights();
		if(h.length==0) return false;
		
		for(int i=0;i<h.length;i++)
		{
			int start = h[i].getStartOffset();
			int end = h[i].getEndOffset();
			if(start!=end && start>=p)
			{
				comp.setCaretPosition(end);
				return true;
			}
		}
		return true;
	}
}