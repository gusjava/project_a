package a.entity.gus06.swing.textcomp.highlight.clear;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.Highlighter;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190405";}

	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		Highlighter high = comp.getHighlighter();
		high.removeAllHighlights();
	}
}
