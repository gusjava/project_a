package a.entity.gus06.swing.textcomp.caret.word.find;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Vector;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140915";}


	private Service getRange;

	public EntityImpl() throws Exception
	{
		getRange = Outside.service(this,"gus06.swing.textcomp.caret.word.range");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String s = comp.getSelectedText();
		if(s!=null && !s.equals("")) return s;
		
		String text = comp.getText();
		int length = text.length();
		int pos = comp.getCaretPosition();
		
		int[] range = (int[]) getRange.t(obj);
		return text.substring(range[0]+1,range[1]);
	}
}
