package a.entity.gus06.java.compiler.errorhighlighter;

import a.framework.*;
import javax.swing.text.*;
import java.util.*;
import java.awt.Color;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140727";}

	public static final Color COLOR = new Color(255,204,204);
	
	private class HighlightPainter extends DefaultHighlighter.DefaultHighlightPainter
	{public HighlightPainter(){super(COLOR);}}
	

	
	
	public Object t(Object obj) throws Exception
	{return new Holder((JTextComponent) obj);}
	
	
	
	private class Holder implements P
	{
		private JTextComponent comp;
		private Highlighter high;
		private HighlightPainter painter;
	
		private List list;
	
		public Holder(JTextComponent comp)
		{
			this.comp = comp;
			high = comp.getHighlighter();
			painter = new HighlightPainter();
		}
	
	
		public void p(Object obj) throws Exception
		{
			list = (List) obj;

			removeHighlights();
			for(Object o:list)
			addHighlight((Map)o);
		}
		
		
		private void addHighlight(Map m) throws Exception
		{
			String lineNb_ = get(m,"lineNb");
			String pos_ = get(m,"pos");
		
			int lineNb = Integer.parseInt(lineNb_);
			int pos = Integer.parseInt(pos_);
	
			Element element = comp.getDocument().getDefaultRootElement().getElement(lineNb-1);
			if(element==null) return;
			
			int lineStart = element.getStartOffset();
			int start = lineStart+pos;
			int end = start+1;
	
			add(high,start,end,painter);
		}
		
		
		private String get(Map m, String key) throws Exception
		{
			if(!m.containsKey(key)) throw new Exception("Key not found: "+key);
			return (String) m.get(key);
		}
	
	
		private void removeHighlights()
		{
			Highlighter.Highlight[] h = high.getHighlights();
			for(int i=0;i<h.length;i++)
			if(h[i].getPainter().equals(painter))
			high.removeHighlight(h[i]);
		}
	}
	
	
	
	
	
	private void add(Highlighter high, int start, int end, HighlightPainter painter)
	{
		try {high.addHighlight(start,end,painter);}
		catch(Exception e) {Outside.err(this,"addHighlight(Highlighter,int,int,HighlightPainter)",e);}
	}
}
