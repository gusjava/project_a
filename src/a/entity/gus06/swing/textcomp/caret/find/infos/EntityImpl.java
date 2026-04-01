package a.entity.gus06.swing.textcomp.caret.find.infos;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.text.Element;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151108";}

	
	
	public Object t(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		PlainDocument document = (PlainDocument) comp.getDocument();
		
		int pos = comp.getCaretPosition();
		Element line = document.getParagraphElement(pos);
		
		int start = line.getStartOffset();
		int end = line.getEndOffset();
		
		int x = document.getDefaultRootElement().getElementIndex(pos);
		int y = pos-start;
		
		return new int[]{x,y,pos,start,end};
	}
}
