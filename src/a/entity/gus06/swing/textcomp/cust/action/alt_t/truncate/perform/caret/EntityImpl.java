package a.entity.gus06.swing.textcomp.cust.action.alt_t.truncate.perform.caret;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160903";}


	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		PlainDocument document = (PlainDocument) comp.getDocument();
		int length = document.getLength();
		
		int p = comp.getCaretPosition();
		Element element = document.getParagraphElement(p);
		
		int start = element.getStartOffset();
		int offset = p-start;
		
		String text = comp.getText();
		String[] lines = text.split("\n");
		
		StringBuffer b = new StringBuffer();
		for(String line:lines)
		{
			int len = line.length();
			String line1 = line.substring(0,Math.min(len,offset));
			b.append(line1+"\n");
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		comp.setText(b.toString());
	}
}
