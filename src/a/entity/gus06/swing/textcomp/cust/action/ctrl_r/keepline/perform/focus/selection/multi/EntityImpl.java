package a.entity.gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.focus.selection.multi;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151108";}


	private Service handleRange;
	
	public EntityImpl() throws Exception
	{
		handleRange = Outside.service(this,"gus06.swing.textcomp.textfocus.handlerange");
	}

	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		PlainDocument document = (PlainDocument) comp.getDocument();
		int length = document.getLength();
		
		int p = comp.getCaretPosition();
		int start = findStart(document,comp.getSelectionStart());
		int end = findEnd(document,comp.getSelectionEnd());
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
