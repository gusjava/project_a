package a.entity.gus06.swing.textcomp.cust.action.ctrl_e.doubleline.perform.selection.multi;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160426";}

	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		PlainDocument document = (PlainDocument) comp.getDocument();
		int length = document.getLength();
		
		Element element1 = document.getParagraphElement(comp.getSelectionStart());
		Element element2 = document.getParagraphElement(comp.getSelectionEnd());
		
		int start = element1.getStartOffset();
		int end = element2.getEndOffset();
		
		if(end==length+1)
		{
			String text = document.getText(start,length-start);
			document.insertString(length,"\n"+text,null);
		}
		else
		{
			String text = document.getText(start,end-start);
			document.insertString(end,text,null);
		}
	}
}
