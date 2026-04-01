package a.entity.gus06.swing.textcomp.highlight.find.forpainter;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.text.*;
import java.util.Collections;
import java.util.Comparator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151108";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		Highlighter.HighlightPainter painter = (Highlighter.HighlightPainter) o[1];
		
		List l = new ArrayList();
		
		Highlighter high = comp.getHighlighter();
		Highlighter.Highlight[] ht = high.getHighlights();
		
		for(int i=0;i<ht.length;i++)
		if(ht[i].getPainter().equals(painter)) l.add(ht[i]);
		
		Collections.sort(l,new Comparator1());
		return l;
	}
	
	
	private class Comparator1 implements Comparator
	{
		public int compare(Object o1, Object o2)
		{
			int i1 = ((Highlighter.Highlight) o1).getStartOffset();
			int i2 = ((Highlighter.Highlight) o2).getStartOffset();
			
			return i1<i2 ? -1 : i1==i2 ? 0 : 1;
		}
	}
}
