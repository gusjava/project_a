package a.entity.gus06.swing.textcomp.cust.action.ctrl_j.high1.perform.selection;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160506";}

	public static final String PAINTER_KEY = "high1";
	

	private Service getPainter;
	private DefaultHighlighter.DefaultHighlightPainter painter;
	
	public EntityImpl() throws Exception
	{
		getPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.manager1");
		painter = (DefaultHighlighter.DefaultHighlightPainter) getPainter.r(PAINTER_KEY);
	}

	
	
	
	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		Highlighter high = comp.getHighlighter();
		PlainDocument document = (PlainDocument) comp.getDocument();
		
		int start = comp.getSelectionStart();
		int end = comp.getSelectionEnd();
		int length = document.getLength();
		
		Element element1 = document.getParagraphElement(start);
		
		int start1 = element1.getStartOffset();
		int end1 = element1.getEndOffset();
		
		if(end>=end1) return;
		
		boolean endOfLine = end==end1-1;
		
		int d1 = start-start1;
		int d2 = end-start1;
		
		Element root = document.getDefaultRootElement();
		int number = root.getElementCount();
		
		for(int i=0;i<number;i++)
		{
			boolean last = i==number-1;
			
			Element element0 = root.getElement(i);
			int start0 = element0.getStartOffset();
			int end0 = element0.getEndOffset();
			if(!last) end0--;
			
			int a1 = Math.min(start0+d1,end0);
			int a2 = endOfLine ? end0 : Math.min(start0+d2,end0);
			
			if(a1!=a2)  high.addHighlight(a1,a2,painter);
		}
	}
}
