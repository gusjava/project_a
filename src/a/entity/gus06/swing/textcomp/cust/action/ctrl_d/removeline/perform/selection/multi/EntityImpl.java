package a.entity.gus06.swing.textcomp.cust.action.ctrl_d.removeline.perform.selection.multi;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151105";}

	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		PlainDocument document = (PlainDocument) comp.getDocument();
		Element root = document.getDefaultRootElement();
		int length = document.getLength();
		
		int startLine = root.getElementIndex(comp.getSelectionStart());
		int endLine = root.getElementIndex(comp.getSelectionEnd());
		
		Element element1 = document.getParagraphElement(comp.getSelectionStart());
		Element element2 = document.getParagraphElement(comp.getSelectionEnd());
		
		int start = element1.getStartOffset()-1;
		int end = element2.getEndOffset()-1;
		
		if(start<0) {start++;end++;}
		if(end>length) end = length;
		
		document.remove(start,end-start);
	}
}
