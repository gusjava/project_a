package a.entity.gus06.swing.textcomp.cust.action.del.perform;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160427";}


	private Service findPainter;
	private Service highlight;

	public EntityImpl() throws Exception
	{
		findPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.findatposition");
		highlight = Outside.service(this,"gus06.swing.textcomp.cust.action.del.perform.highlightpainter");
	}
	
	
	public void p(Object obj) throws Exception
	{
		perform((JTextComponent) obj);
	}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		if(hasSelection(comp))
		{
			int start = comp.getSelectionStart();
			int end = comp.getSelectionEnd();
			int length = comp.getDocument().getLength();
			if(end>length) end = length;
			
			comp.getDocument().remove(start,end-start);
			return;
		}
		
		Object painter = findPainter.t(comp);
		if(painter!=null)
		{
			highlight.p(new Object[]{comp,painter});
			return;
		}
		
		int pos = comp.getCaretPosition();
		int length = comp.getDocument().getLength();
		if(pos==length) return;
		
		comp.getDocument().remove(pos,1);
	}
	
	
	private boolean hasSelection(JTextComponent comp)
	{
		String s = comp.getSelectedText();
		return s!=null && !s.equals("");
	}
}
