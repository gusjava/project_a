package a.entity.gus06.swing.textcomp.cust.action.ctrl_h.tool.perform.highlightpainter;

import a.framework.*;
import javax.swing.text.*;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160427";}


	private Service findForPainter;

	public EntityImpl() throws Exception
	{
		findForPainter = Outside.service(this,"gus06.swing.textcomp.highlight.find.forpainter");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		Highlighter.HighlightPainter painter = (Highlighter.HighlightPainter) o[1];
		T t = (T) o[2];
		
		List l = (List) findForPainter.t(new Object[]{comp,painter});
		
		((V) comp).v("undoable","true");
		
		for(int i=l.size()-1;i>=0;i--)
		{
			Highlighter.Highlight ht = (Highlighter.Highlight) l.get(i);
			
			int start = ht.getStartOffset();
			int end = ht.getEndOffset();
			
			applyTransform(comp,start,end,t);
		}
		
		((V) comp).v("undoable","false");
	}
	
	
	
	private void applyTransform(JTextComponent comp, int start, int end, T t) throws Exception
	{
		String text = comp.getText(start,end-start);
		String text1 = (String) t.t(text);
		if(text1==null) return;
		
		end = Math.min(end,comp.getDocument().getLength());
		
		comp.getDocument().insertString(end,text1,null);
		comp.getDocument().remove(start,end-start);
	}
}
