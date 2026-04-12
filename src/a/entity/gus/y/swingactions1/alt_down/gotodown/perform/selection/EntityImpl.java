package a.entity.gus.y.swingactions1.alt_down.gotodown.perform.selection;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240121";}

	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	private void perform(JTextComponent comp) throws Exception
	{
		String selection = comp.getSelectedText();
		String text = comp.getText();
		int p = comp.getSelectionEnd();
		
		String text1 = text.substring(p);
		int offset = text1.lastIndexOf(selection);
		
		if(offset==-1) return;
		
		comp.setSelectionStart(p+offset);
		comp.setSelectionEnd(p+offset+selection.length());
	}
}
