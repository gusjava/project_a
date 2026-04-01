package a.entity.gus06.swing.textcomp.cust.action.ctrl_g.regex.perform;

import a.framework.*;
import javax.swing.text.*;
import java.util.regex.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141105";}

	public static final String PAINTER_KEY = "regex";


	private Service getPainter;
	private Service findExp;
	
	private DefaultHighlighter.DefaultHighlightPainter painter;
	
	
	
	public EntityImpl() throws Exception
	{
		getPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.manager1");
		findExp = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_g.regex.perform.findexp");
		
		painter = (DefaultHighlighter.DefaultHighlightPainter) getPainter.r(PAINTER_KEY);
	}
	
	
	
	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		Highlighter high = comp.getHighlighter();
		boolean removed = removeHighlights(high);
		if(removed) return;
		
		String exp = (String) findExp.t(comp);
		if(exp==null) return;
		
		Pattern p = Pattern.compile(exp,Pattern.DOTALL);
		String text = comp.getText();
		Matcher m = p.matcher(text);
		
		int group = m.groupCount()>0?1:0;
		
		while(m.find())
		{
		    int start = m.start(group);
		    int end = m.end(group);
		    high.addHighlight(start,end,painter);
		}
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
