package a.entity.gus06.swing.textcomp.cust.action.alt_up.gotoup.perform.selection;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160904";}

	
	
	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		String selection = comp.getSelectedText();
		String text = comp.getText();
		int p = comp.getSelectionEnd();
		
		String text1 = text.substring(0,p);
		int offset = text1.indexOf(selection);
		
		if(offset==-1) return;
		
		comp.setSelectionStart(offset);
		comp.setSelectionEnd(offset+selection.length());
	}
}
