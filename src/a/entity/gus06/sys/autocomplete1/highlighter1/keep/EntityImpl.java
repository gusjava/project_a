package a.entity.gus06.sys.autocomplete1.highlighter1.keep;

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
			
			if(end0 <= start)
			{
				high.removeHighlight(h[i]);
			}
			else if(start0 >= end)
			{
				high.removeHighlight(h[i]);
			}
			else
			{
				int start1 = Math.max(start0,start);
				int end1 = Math.min(end0,end);
				high.changeHighlight(h[i],start1,end1);
			}
		}
	}
}
