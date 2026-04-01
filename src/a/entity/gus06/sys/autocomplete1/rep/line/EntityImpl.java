package a.entity.gus06.sys.autocomplete1.rep.line;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20160519";}


	private Service clipboard;

	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.clipboard.access.string");
	}

	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		PlainDocument document = (PlainDocument) comp.getDocument();
		
		String selected = comp.getSelectedText();
		if(selected==null || selected.equals("")) return;
		
		int pos = comp.getCaretPosition();
		
		int start = findStart(document,pos);
		int end = findEnd(document,pos);
		String line = comp.getText(start,end-1-start);
		
		line = line.replace(selected,key);
		
		comp.select(start,end-1);
		comp.replaceSelection(line);
	}
	
	
	public void p(Object obj) throws Exception
	{
		String value = (String) clipboard.g();
		v(value,obj);
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
