package a.entity.gus06.sys.autocomplete1.repeatline.selection.mono;

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
			{
				if(nb>1)
				{
					for(int j=0;j<nb;j++)
					document.insertString(end,line,null);
				}
				else if(nb==0)
				{
					//remove
				}
			}
		}
		
		((V) comp).v("undoable","false");
	}
}