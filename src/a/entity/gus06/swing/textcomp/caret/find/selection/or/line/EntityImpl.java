package a.entity.gus06.swing.textcomp.caret.find.selection.or.line;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.text.Element;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220825";}

	
	
	public Object t(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		PlainDocument document = (PlainDocument) comp.getDocument();
		if(hasSelection(comp)) return comp.getSelectedText();
		
		int pos = comp.getCaretPosition();
		Element element = document.getParagraphElement(pos);
		int start = element.getStartOffset();
		int end = element.getEndOffset();
		
		return document.getText(start,end-start);
	}
	
	private boolean hasSelection(JTextComponent comp)
	{
		String s = comp.getSelectedText();
		return s!=null && !s.equals("");
	}
}