package a.entity.gus06.swing.textcomp.cust.action.ctrl_q.keepselection.perform.focus.caret.painter;

import a.framework.*;
import javax.swing.text.*;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151107";}

	private Service handleRangeMulti;
	private Service findHigh;

	public EntityImpl() throws Exception
	{
		handleRangeMulti = Outside.service(this,"gus06.swing.textcomp.textfocus.handlerange.multi");
		findHigh = Outside.service(this,"gus06.swing.textcomp.highlight.find.forpainter");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		
		List high = (List) findHigh.t(obj);
		int length = comp.getDocument().getLength();
		
		int[] range = new int[high.size()*2];
		
		for(int i=0;i<high.size();i++)
		{
			Highlighter.Highlight h = (Highlighter.Highlight) high.get(i);
			int start = h.getStartOffset();
			int end = h.getEndOffset();
			
			if(end>length) end = length;
			
			range[2*i] = start;
			range[2*i+1] = end;
		}
		handleRangeMulti.p(new Object[]{comp,range});
	}
}
