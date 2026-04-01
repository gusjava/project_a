package a.entity.gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.caret;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151104";}


	private Service highlight;
	private Service keepWord;
	private Service findPainter;

	public EntityImpl() throws Exception
	{
		highlight = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.caret.painter");
		keepWord = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.caret.keepword");
		findPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.findatposition");
	}

	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		PlainDocument document = (PlainDocument) comp.getDocument();
		Element root = document.getDefaultRootElement();
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
		
		keepText(comp,start,end);
		comp.setCaretPosition(p-start);
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
	
	private void keepText(JTextComponent comp, int start, int end) throws Exception
	{
		String text = comp.getText(start,end-1-start);
		comp.setText(text);
	}
}
