package a.entity.gus06.swing.textcomp.highlight.remove.color;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.Highlighter;
import java.awt.Color;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180223";}


	private Service findPainter;

	public EntityImpl() throws Exception
	{
		findPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.find");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		Highlighter.HighlightPainter painter = findPainter(o[1]);
		
		Highlighter high = comp.getHighlighter();
		Highlighter.Highlight[] h = high.getHighlights();
		
		for(int i=0;i<h.length;i++)
		if(h[i].getPainter().equals(painter)) high.removeHighlight(h[i]);
	}
	
	
	private Highlighter.HighlightPainter findPainter(Object obj) throws Exception
	{return (Highlighter.HighlightPainter) findPainter.t(obj);}
}