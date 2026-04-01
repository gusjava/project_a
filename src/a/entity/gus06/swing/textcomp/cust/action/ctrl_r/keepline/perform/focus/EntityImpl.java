package a.entity.gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.focus;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151108";}


	private Service selectionMono;
	private Service selectionMulti;
	private Service caret;


	public EntityImpl() throws Exception
	{
		selectionMono = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.focus.selection.mono");
		selectionMulti = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.focus.selection.multi");
		caret = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.focus.caret");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof JTextArea)
		perform((JTextComponent) obj);
	}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		if(hasSelectionMulti(comp))
		{
			selectionMulti.p(comp);
			return;
		}
		if(hasSelection(comp))
		{
			selectionMono.p(comp);
			return;
		}
		caret.p(comp);
	}
	
	
	private boolean hasSelection(JTextComponent comp)
	{
		String s = comp.getSelectedText();
		return s!=null && !s.equals("");
	}
	
	private boolean hasSelectionMulti(JTextComponent comp)
	{
		String s = comp.getSelectedText();
		return s!=null && s.contains("\n");
	}
}
