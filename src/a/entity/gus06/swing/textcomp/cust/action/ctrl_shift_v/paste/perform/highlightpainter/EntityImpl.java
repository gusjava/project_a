package a.entity.gus06.swing.textcomp.cust.action.ctrl_shift_v.paste.perform.highlightpainter;

import a.framework.*;
import javax.swing.text.*;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200326";}


	private Service clipboard;
	private Service findForPainter;

	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.sys.clipboardwatcher1.chooser2");
		findForPainter = Outside.service(this,"gus06.swing.textcomp.highlight.find.forpainter");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		Highlighter.HighlightPainter painter = (Highlighter.HighlightPainter) o[1];
		
		String s = (String) clipboard.g();
		if(s==null) return;
		
		String[] n = s.split("\n");
		int lastIndex = n.length-1;
		
		List l = (List) findForPainter.t(new Object[]{comp,painter});
		
		((V) comp).v("undoable","true");
		
		for(int i=l.size()-1;i>=0;i--)
		{
			Highlighter.Highlight ht = (Highlighter.Highlight) l.get(i);
			
			int start = ht.getStartOffset();
			int end = ht.getEndOffset();
			String value = n[Math.min(i,lastIndex)];
			
			change(comp,start,end,value);
		}
		
		((V) comp).v("undoable","false");
	}
	
	
	private void change(JTextComponent comp, int start, int end, String s) throws Exception
	{
		comp.getDocument().insertString(end,s,null);
		comp.getDocument().remove(start,end-start);
	}
	
}
