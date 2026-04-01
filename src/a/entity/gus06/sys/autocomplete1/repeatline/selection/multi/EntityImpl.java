package a.entity.gus06.sys.autocomplete1.repeatline.selection.multi;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20201130";}

	
	
	public void v(String key, Object obj) throws Exception
	{
		int nb = Integer.parseInt(key);
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
			
			if(nb>1)
			{
				for(int i=1;i<nb;i++)
				document.insertString(length,"\n"+text,null);
			}
		}
		else
		{
			String text = document.getText(start,end-start);
			
			if(nb>1)
			{
				for(int i=1;i<nb;i++)
				document.insertString(end,text,null);
			}
		}
	}
}