package a.entity.gus06.swing.textcomp.cust.action.ctrl_d.removeline.perform.caret;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151105";}


	private Service highlight;
	private Service findPainter;

	public EntityImpl() throws Exception
	{
		highlight = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_d.removeline.perform.caret.highlightpainter");
		findPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.findatposition");
	}

	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		Object painter = findPainter.t(comp);
		if(painter!=null)
		{
			highlight.p(new Object[]{comp,painter});
			return;
		}
		
		PlainDocument document = (PlainDocument) comp.getDocument();
		int length = document.getLength();
		int p = comp.getCaretPosition();
		Element element = document.getParagraphElement(p);
		
		int start = element.getStartOffset()-1;
		int end = element.getEndOffset()-1;
		
		if(start<0) {start++;end++;}
		if(end>length) end = length;
		document.remove(start,end-start);
	}
}
