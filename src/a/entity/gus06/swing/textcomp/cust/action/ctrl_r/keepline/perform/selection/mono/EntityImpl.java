package a.entity.gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.selection.mono;

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
		Element root = document.getDefaultRootElement();
		int number = root.getElementCount();
		
		String selection = comp.getSelectedText();
		
		if(number==1)
		{
			comp.setText(selection);
			return;
		}
		
		int p1 = 0;
		int removed = 0;
		int p = comp.getCaretPosition();
		Element caretEl = document.getParagraphElement(p);
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<number;i++)
		{
			Element element = root.getElement(i);
			if(element==caretEl) p1 = p-removed;
			
			String line = elementToLine(comp,element);
			if(line.contains(selection)) b.append(line);
			else removed += line.length();
		}
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		comp.setText(b.toString());
		comp.setCaretPosition(p1);
	}
	
	
	
	private String elementToLine(JTextComponent comp, Element element) throws Exception
	{
		int start = element.getStartOffset();
		int end = element.getEndOffset();
		return comp.getText(start,end-start);
	}
}
