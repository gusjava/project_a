package a.entity.gus06.swing.textcomp.cust.action.ctrl_d.removeline.perform.selection.mono;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151105";}


	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		PlainDocument document = (PlainDocument) comp.getDocument();
		Element root = document.getDefaultRootElement();
		int length = document.getLength();
		int number = root.getElementCount();
		
		String selection = comp.getSelectedText();
		
		((V) comp).v("undoable","true");
		
		for(int i=number-1;i>=0;i--)
		{
			Element element = root.getElement(i);
			int start = element.getStartOffset();
			int end = element.getEndOffset();
			if(end>length) end = length;
		
			String line = comp.getText(start,end-start);
			if(line.contains(selection))
			document.remove(start,end-start);
		}
		
		((V) comp).v("undoable","false");
	}
}
