package a.entity.gus06.swing.textcomp.caret.find.lineindex;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.text.Element;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201130";}

	
	
	public Object t(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		PlainDocument document = (PlainDocument) comp.getDocument();
		
		int pos = comp.getCaretPosition();
		int index = document.getDefaultRootElement().getElementIndex(pos);
		return Integer.valueOf(index);
	}
}