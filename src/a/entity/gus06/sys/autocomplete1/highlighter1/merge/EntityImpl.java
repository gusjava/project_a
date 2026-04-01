package a.entity.gus06.sys.autocomplete1.highlighter1.merge;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160517";}

	public static final String PAINTER_KEY = "f1";


	private Service getPainter;
	private DefaultHighlighter.DefaultHighlightPainter painter;


	public EntityImpl() throws Exception
	{
		getPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.manager1");
		painter = (DefaultHighlighter.DefaultHighlightPainter) getPainter.r(PAINTER_KEY);
	}

	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		Highlighter high = comp.getHighlighter();
		Highlighter.Highlight[] h = high.getHighlights();
		for(int i=0;i<h.length;i++)
		{
			int start = h[i].getStartOffset();
			int end = h[i].getEndOffset();
			
			high.removeHighlight(h[i]);
			high.addHighlight(start,end,painter);
		}
	}
}
