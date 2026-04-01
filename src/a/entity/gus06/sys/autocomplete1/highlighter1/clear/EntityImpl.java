package a.entity.gus06.sys.autocomplete1.highlighter1.clear;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.Highlighter;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160516";}

	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		Highlighter high = comp.getHighlighter();
		high.removeAllHighlights();
	}
}
