package a.entity.gus06.sys.autocomplete1.repeatline.caret;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20201130";}


	
	
	public void v(String key, Object obj) throws Exception
	{
		int nb = Integer.parseInt(key);
		JTextComponent comp = (JTextComponent) obj;
		
		PlainDocument document = (PlainDocument) comp.getDocument();
		int length = document.getLength();
		
		int p = comp.getCaretPosition();
		Element element = document.getParagraphElement(p);
		
		int start = element.getStartOffset()-1;
		int end = element.getEndOffset()-1;
		
		if(start<0) {start++;}
		if(end>length) end = length;
		
		String line = document.getText(start,end-start);
		if(!line.startsWith("\n")) line = "\n"+line;
		
		if(nb>1)
		{
			for(int i=0;i<nb-1;i++)
			document.insertString(end,line,null);
		}
		else if(nb==0)
		{
			document.remove(start,end-start);
		}
	}
}