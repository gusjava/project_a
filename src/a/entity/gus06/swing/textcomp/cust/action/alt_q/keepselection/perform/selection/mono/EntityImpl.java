package a.entity.gus06.swing.textcomp.cust.action.alt_q.keepselection.perform.selection.mono;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.text.Element;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160902";}
	
	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		PlainDocument document = (PlainDocument) comp.getDocument();
		int length = document.getLength();
		
		int p1 = comp.getSelectionStart();
		int p2 = comp.getSelectionEnd();
		
		Element element = document.getParagraphElement(p1);
		
		int start = element.getStartOffset();
		int offset1 = p1-start;
		int offset2 = p2-start;
		
		String text = comp.getText();
		String[] lines = text.split("\n");
		
		StringBuffer b = new StringBuffer();
		for(String line:lines)
		{
			int len = line.length();
			int r1 = Math.min(len,offset1);
			int r2 = Math.min(len,offset2);
			
			String line1 = line.substring(r1,r2);
			b.append(line1+"\n");
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		comp.setText(b.toString());
	}
}
