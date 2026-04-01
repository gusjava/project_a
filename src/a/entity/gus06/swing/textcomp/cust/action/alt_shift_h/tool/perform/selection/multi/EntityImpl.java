package a.entity.gus06.swing.textcomp.cust.action.alt_shift_h.tool.perform.selection.multi;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160903";}


	private Service chooseTrans;


	public EntityImpl() throws Exception
	{
		chooseTrans = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_shift_h.tool.perform.chooser");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		T t = (T) chooseTrans.t(comp);
		if(t==null) return;
		
		PlainDocument document = (PlainDocument) comp.getDocument();
		
		int p = comp.getCaretPosition();
		int start = findStart(document,comp.getSelectionStart());
		int end = findEnd(document,comp.getSelectionEnd());
		
		String text = comp.getText(start,end-1-start);
		String text0 = transformText(text,t);
		
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
	
	private String transformText(String text, T t) throws Exception
	{
		String[] lines = text.split("\n");
		int nb = lines.length;
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nb;i++)
		{
			String line1 = (String) t.t(lines[i]);
			b.append(line1+"\n");
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
