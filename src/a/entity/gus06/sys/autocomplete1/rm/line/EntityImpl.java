package a.entity.gus06.sys.autocomplete1.rm.line;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20160519";}

	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		PlainDocument document = (PlainDocument) comp.getDocument();
		int pos = comp.getCaretPosition();
		
		int start = findStart(document,pos);
		int end = findEnd(document,pos);
		String line = comp.getText(start,end-1-start);
		
		line = line.replace(key,"");
		
		comp.select(start,end-1);
		comp.replaceSelection(line);
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String s = comp.getSelectedText();
		if(s==null || s.equals("")) return;
		
		v(s,comp);
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
