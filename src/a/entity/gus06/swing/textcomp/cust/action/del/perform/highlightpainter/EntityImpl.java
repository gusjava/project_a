package a.entity.gus06.swing.textcomp.cust.action.del.perform.highlightpainter;

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
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		Highlighter.HighlightPainter painter = (Highlighter.HighlightPainter) o[1];
		
		((V) comp).v("undoable","true");
		
		int length = comp.getDocument().getLength();
		List l = (List) findForPainter.t(new Object[]{comp,painter});
		
		for(int i=l.size()-1;i>=0;i--)
		{
			Highlighter.Highlight ht = (Highlighter.Highlight) l.get(i);
			
			int start = ht.getStartOffset();
			int end = ht.getEndOffset();
			if(end>length) end = length;
			
			comp.getDocument().remove(start,end-start);
		}
		
		((V) comp).v("undoable","false");
	}
	
}
