package a.entity.gus06.swing.textcomp.cust.action.alt_backspace.perform.selection.multi;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160906";}


	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		PlainDocument document = (PlainDocument) comp.getDocument();
		
		int p = comp.getCaretPosition();
		int r0 = comp.getSelectionStart();
		int r1 = comp.getSelectionEnd();
		
		int start0 = findStart(document,p);
		int start = findStart(document,r0);
		int end = findEnd(document,r1);
		
		int offset = p-start0;
		if(offset==0) return;
		
		String text = comp.getText(start,end-1-start);
		String text0 = transformText(text,offset);
		
		int delta = text.length()-text0.length();
		
		document.remove(start,end-1-start);
		document.insertString(start,text0,null);
		
		comp.select(r0,r1-delta);
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
		int nb = lines.length;
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nb;i++)
		{
			String line = lines[i];
			int len = line.length();
			if(len<=offset-1) b.append(line+"\n");
			else
			{
				String part1 = line.substring(0,offset-1);
				String part2 = line.substring(offset,len);
				b.append(part1+part2+"\n");
			}
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
