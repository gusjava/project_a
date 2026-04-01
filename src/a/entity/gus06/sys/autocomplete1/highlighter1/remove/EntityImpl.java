package a.entity.gus06.sys.autocomplete1.highlighter1.remove;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.Highlighter;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160516";}

	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		Highlighter high = comp.getHighlighter();
		int start = comp.getSelectionStart();
		int end = comp.getSelectionEnd();
		
		Highlighter.Highlight[] h = high.getHighlights();
		for(int i=0;i<h.length;i++)
		{
			int start0 = h[i].getStartOffset();
			int end0 = h[i].getEndOffset();
			Highlighter.HighlightPainter painter  = h[i].getPainter();
			
			if(end0 <= start)
			{
				
			}
			else if(start0 >= end)
			{
				
			}
			else
			{
				high.removeHighlight(h[i]);
				
				if(start0 < start)
				high.addHighlight(start0,start,painter);
				
				if(end0 > end)
				high.addHighlight(end,end0,painter);
			}
		}
	}
}
