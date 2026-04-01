package a.entity.gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.focus.caret;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151108";}


	private Service highlight;
	private Service keepWord;
	private Service findPainter;
	private Service handleRange;
	

	public EntityImpl() throws Exception
	{
		highlight = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.focus.caret.painter");
		keepWord = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.focus.caret.keepword");
		findPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.findatposition");
		handleRange = Outside.service(this,"gus06.swing.textcomp.textfocus.handlerange");
	}

	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		PlainDocument document = (PlainDocument) comp.getDocument();
		Element root = document.getDefaultRootElement();
		int length = document.getLength();
		int number = root.getElementCount();
		
		if(number==1)
		{
			keepWord.p(comp);
			return;
		}
		
		Object painter = findPainter.t(comp);
		if(painter!=null)
		{
			highlight.p(new Object[]{comp,painter});
			return;
		}
		
		int p = comp.getCaretPosition();
		int start = findStart(document,p);
		int end = findEnd(document,p);
		end--;
		
		int[] range = new int[]{start,end};
		handleRange.p(new Object[]{comp,range});
	}
	
	
	
	
	private int findStart(PlainDocument document, int p)
	{
		Element el = document.getParagraphElement(p);
		return el.getStartOffset();
	}
	
	private int findEnd(PlainDocument document, int p)
	{
		Element el = document.getParagraphElement(p);
		return el.getEndOffset();
	}
}
