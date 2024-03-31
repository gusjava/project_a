package a.entity.gus.y.swingactions1.alt_left.gotoleft.perform.selection;

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
		int p = comp.getSelectionStart();
		
		String text1 = text.substring(0,p);
		int offset = text1.lastIndexOf(selection);
		
		if(offset==-1) return false;
		
		comp.setSelectionStart(offset);
		comp.setSelectionEnd(offset+selection.length());
		return true;
	}
}