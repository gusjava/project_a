package a.entity.gus06.swing.textcomp.caret.xmltag.range;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170225";}

	
	
	public Object t(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String s = comp.getSelectedText();
		if(s!=null && !s.equals(""))
		{
			return new int[]{
				comp.getSelectionStart(),
				comp.getSelectionEnd()
			};
		}
		
		int pos = comp.getCaretPosition();
		String text = comp.getText();
		int length = text.length();
		
		int start = pos-1;
		int end = pos;
	
		while(start>0 && text.charAt(start)!='<') start--;
		while(end<length-1 && text.charAt(end)!='>') end++;
		
		if(start==0) return null;
		if(end==length-1) return null;
		
		return new int[]{start-1,end+1};
	}
}
