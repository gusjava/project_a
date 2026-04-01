package a.entity.gus06.swing.textcomp.highlight.count.color;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.Highlighter;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231121";}


	private Service findPainter;

	public EntityImpl() throws Exception
	{
		findPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.find");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		Highlighter.HighlightPainter painter = findPainter(o[1]);
		
		Highlighter high = comp.getHighlighter();
		Highlighter.Highlight[] h = high.getHighlights();
		
		int count = 0;
		for(int i=0;i<h.length;i++)
		if(h[i].getPainter().equals(painter)) count++;
		return Integer.valueOf(count);
	}
	
	
	private Highlighter.HighlightPainter findPainter(Object obj) throws Exception
	{return (Highlighter.HighlightPainter) findPainter.t(obj);}
}