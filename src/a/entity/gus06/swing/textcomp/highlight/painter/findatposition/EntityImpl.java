package a.entity.gus06.swing.textcomp.highlight.painter.findatposition;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151105";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof JTextComponent)
			return find((JTextComponent) obj);
		if(obj instanceof Object[])
			return find((Object[]) obj);
			
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object find(JTextComponent comp)
	{
		int pos = comp.getCaretPosition();
		return find(comp,pos);
	}
	
	
	private Object find(Object[] o) throws Exception
	{
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		JTextComponent comp = (JTextComponent) o[0];
		Integer pos = (Integer) o[1];
		return find(comp,pos.intValue());
	}
	
	
	
	private Object find(JTextComponent comp, int pos)
	{
		Highlighter high = comp.getHighlighter();
		Highlighter.Highlight[] ht = high.getHighlights();
		for(int i=ht.length-1;i>=0;i--)
		{
			int start = ht[i].getStartOffset();
			int end = ht[i].getEndOffset();
			
			if(start<=pos && pos<=end) return ht[i].getPainter();
		}
		return null;
	}
}
