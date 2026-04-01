package a.entity.gus06.swing.textcomp.cust.action.alt_t.truncate.perform.selection.multi;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.JTextArea;
import javax.swing.text.PlainDocument;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160903";}


	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		PlainDocument document = (PlainDocument) comp.getDocument();
		
		int p = comp.getCaretPosition();
		int p0 = findStart(document,p);
		int offset = p-p0;
		
		int start = findStart(document,comp.getSelectionStart());
		int end = findEnd(document,comp.getSelectionEnd());
		
		String text = comp.getText(start,end-1-start);
		String text0 = transformText(text,offset);
		
		document.remove(start,end-1-start);
		document.insertString(start,text0,null);
		
		comp.setCaretPosition(start+text0.length());
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
	
	
	
	private String transformText(String text, int offset)
	{
		String[] lines = text.split("\n");
		
		StringBuffer b = new StringBuffer();
		for(String line:lines)
		{
			int len = line.length();
			String line1 = line.substring(0,Math.min(len,offset));
			b.append(line1+"\n");
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
