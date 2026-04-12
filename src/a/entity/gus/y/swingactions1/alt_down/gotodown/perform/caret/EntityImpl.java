package a.entity.gus.y.swingactions1.alt_down.gotodown.perform.caret;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240121";}

	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	private void perform(JTextComponent comp) throws Exception
	{
		int p = comp.getCaretPosition();
		
		Highlighter high = comp.getHighlighter();
		Highlighter.Highlight[] h = high.getHighlights();
		for(int i=h.length-1;i>=0;i--)
		{
			int start = h[i].getStartOffset();
			int end = h[i].getEndOffset();
			if(start<=p) break;
			
			if(start!=end)
			{
				comp.setCaretPosition(end);
				return;
			}
		}
		comp.setCaretPosition(comp.getText().length());
	}
}
