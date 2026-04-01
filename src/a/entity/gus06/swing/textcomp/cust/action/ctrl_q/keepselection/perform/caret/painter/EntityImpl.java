package a.entity.gus06.swing.textcomp.cust.action.ctrl_q.keepselection.perform.caret.painter;

import a.framework.*;
import javax.swing.text.*;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151105";}

	private Service findHigh;

	public EntityImpl() throws Exception
	{
		findHigh = Outside.service(this,"gus06.swing.textcomp.highlight.find.forpainter");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		
		List high = (List) findHigh.t(obj);
		
		PlainDocument document = (PlainDocument) comp.getDocument();
		int length = document.getLength();
		
		((V) comp).v("undoable","true");
		
		int offset = length;
		
		for(int i=high.size()-1;i>=0;i--)
		{
			Highlighter.Highlight h = (Highlighter.Highlight) high.get(i);
			int start = h.getStartOffset();
			int end = h.getEndOffset();
			
			if(end>length) end = length;
			
			if(offset>end)
			{
				if(offset == length)
					document.remove(end,offset-end);
				else document.replace(end,offset-end,"\n",null);
			}
			offset = start;
		}
		
		if(offset>0) document.remove(0,offset);
		
		((V) comp).v("undoable","false");
	}
}
