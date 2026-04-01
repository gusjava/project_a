package a.entity.gus06.swing.textcomp.highlight.painter.find;

import a.framework.*;
import javax.swing.text.Highlighter;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231121";}

	private Service findPainter;

	public EntityImpl() throws Exception
	{
		findPainter = Outside.service(this,"gus06.swing.textcomp.highlight.painter.builder1.cache");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Highlighter.HighlightPainter)
			return (Highlighter.HighlightPainter) obj;
		if(obj instanceof Color)
			return (Highlighter.HighlightPainter) findPainter.t(obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}