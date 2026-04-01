package a.entity.gus06.swing.textcomp.highlight.sys1.support;

import a.framework.*;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.JTextComponent;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140728";}

	
	
	public Object t(Object obj) throws Exception
	{
		S1 s1 = new S1();
		((JTextComponent) obj).setHighlighter(new Highlighter0(s1));
		return s1;
	}
	
	
	
	public class Highlighter0 extends DefaultHighlighter
	{
		private S1 s1;
		public Highlighter0(S1 s1) throws Exception
		{super();this.s1 = s1;}
    
		public Object addHighlight(int p0, int p1, HighlightPainter p) throws BadLocationException
		{
			Object obj = super.addHighlight(p0,p1,p);
			s1.send(this,"addHighlight(int,int,HighlightPainter)");
			return obj;
		}

		public void removeHighlight(Object tag)
		{
			super.removeHighlight(tag);
			s1.send(this,"removeHighlight(Object)");
		}

		public void removeAllHighlights()
		{
			super.removeAllHighlights();
			s1.send(this,"removeAllHighlights()");
		}

		public void changeHighlight(Object tag, int p0, int p1) throws BadLocationException
		{
			super.changeHighlight(tag,p0,p1);
			s1.send(this,"changeHighlight(Object,int,int)");
		}
	}
}
