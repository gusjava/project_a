package a.entity.gus06.sys.autocomplete1.highlighter1.add;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160516";}

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
		int start = comp.getSelectionStart();
		int end = comp.getSelectionEnd();
		
		high.addHighlight(start,end,painter);
	}
}
