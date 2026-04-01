package a.entity.gus06.swing.textcomp.cust.action.ctrl_j.high1.perform;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160427";}

	public static final String PAINTER_KEY = "high1";
	

	private Service performCaret;
	private Service performSelection;
	private Service performAll;
	private Service getPainter;
	
	private DefaultHighlighter.DefaultHighlightPainter painter;
	
	
	
	public EntityImpl() throws Exception
	{
		performCaret = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_j.high1.perform.caret");
		performSelection = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_j.high1.perform.selection");
		performAll = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_j.high1.perform.all");
		
		getPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.manager1");
		painter = (DefaultHighlighter.DefaultHighlightPainter) getPainter.r(PAINTER_KEY);
	}

	
	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		Highlighter high = comp.getHighlighter();
		boolean removed = removeHighlights(high);
		if(removed) return;
		
		String selected = comp.getSelectedText();
		if(selected==null || selected.equals(""))
			performCaret.p(comp);
		else if(selected.equals(comp.getText()))
			performAll.p(comp);
		else performSelection.p(comp);
	}
	


	private boolean removeHighlights(Highlighter high)
	{
		Highlighter.Highlight[] h = high.getHighlights();
		boolean removed = false;
		for(int i=0;i<h.length;i++)
		if(h[i].getPainter().equals(painter))
		{
			high.removeHighlight(h[i]);
			removed = true;
		}
		return removed;
	}
}
