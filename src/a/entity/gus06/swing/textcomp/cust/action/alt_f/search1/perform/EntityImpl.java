package a.entity.gus06.swing.textcomp.cust.action.alt_f.search1.perform;

import a.framework.*;
import javax.swing.text.*;
import java.util.regex.*;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160803";}

	public static final String PAINTER_KEY1 = "search";
	public static final String PAINTER_KEY2 = "search_i";
	

	private Service getPainter;
	private Service clipboard;
	
	private DefaultHighlighter.DefaultHighlightPainter painter1;
	private DefaultHighlighter.DefaultHighlightPainter painter2;
	
	
	public EntityImpl() throws Exception
	{
		getPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.manager1");
		clipboard = Outside.service(this,"gus06.clipboard.access.string");
		
		painter1 = (DefaultHighlighter.DefaultHighlightPainter) getPainter.r(PAINTER_KEY1);
		painter2 = (DefaultHighlighter.DefaultHighlightPainter) getPainter.r(PAINTER_KEY2);
	}

	
	
	
	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		Highlighter high = comp.getHighlighter();
		removeHighlights(high);
		
		String exp = findExpression();
		if(exp==null || exp.equals("")) return;
		
		Pattern p = Pattern.compile(Pattern.quote(exp),Pattern.DOTALL);
                String text = comp.getText();
                Matcher m = p.matcher(text);
                
                while(m.find())
                {
                    int start = m.start();
                    int end = m.end();
                    high.addHighlight(start,end,painter1);
                }
	}
	
	

	private void removeHighlights(Highlighter high)
	{
		Highlighter.Highlight[] h = high.getHighlights();
		for(int i=0;i<h.length;i++)
		if(h[i].getPainter().equals(painter1) || h[i].getPainter().equals(painter2))
			high.removeHighlight(h[i]);
	}
	
	
	
	
	private String findExpression() throws Exception
	{return (String) clipboard.g();}
}
