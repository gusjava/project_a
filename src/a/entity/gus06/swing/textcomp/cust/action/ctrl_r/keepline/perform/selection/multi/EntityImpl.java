package a.entity.gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.selection.multi;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151104";}


	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		PlainDocument document = (PlainDocument) comp.getDocument();
		
		int p = comp.getCaretPosition();
		int start = findStart(document,comp.getSelectionStart());
		int end = findEnd(document,comp.getSelectionEnd());
			
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
