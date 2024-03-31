package a.entity.gus.y.swingactions1.alt_right.gotoright.perform.selection;

import a.framework.*;
import javax.swing.text.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20240121";}

	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	public boolean f(Object obj) throws Exception
	{return perform((JTextComponent) obj);}
	
	private boolean perform(JTextComponent comp) throws Exception
	{
		String selection = comp.getSelectedText();
		if(selection==null || selection.equals("")) return false;
		
		String text = comp.getText();
		int p = comp.getSelectionEnd();
		
		String text1 = text.substring(p);
		int offset = text1.indexOf(selection);
		
		if(offset==-1) return false;
		
		comp.setSelectionStart(p+offset);
		comp.setSelectionEnd(p+offset+selection.length());
		return true;
	}
}