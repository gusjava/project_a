package a.entity.gus06.swing.textfield.cust.action.ctrl_v.paste.perform;

import a.framework.*;
import javax.swing.JTextField;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220909";}

	private Service clipboard;

	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.clipboard.access.string.or.filepaths");
	}
	
	public void p(Object obj) throws Exception
	{
		perform((JTextField) obj);
	}
	
	private void perform(JTextField comp) throws Exception
	{
		String s = (String) clipboard.g();
		if(s==null) return;
		
		if(hasSelection(comp)) pasteAtSelection(comp, s);
		else pasteAtCaret(comp, s);
	}
	
	
	private boolean hasSelection(JTextField comp)
	{
		String s = comp.getSelectedText();
		return s!=null && !s.equals("");
	}
	
	
	private void pasteAtSelection(JTextField comp, String s) throws Exception
	{
		comp.replaceSelection(s);
	}
	
	private void pasteAtCaret(JTextField comp, String s) throws Exception
	{
		int pos = comp.getCaretPosition();
		comp.getDocument().insertString(pos,s,null);
	}
}