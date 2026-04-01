package a.entity.gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141113";}


	private Service performSelectionMono;
	private Service performSelectionMulti;
	private Service performCaret;
	private Service performFocus;
	private Service focusManager;


	public EntityImpl() throws Exception
	{
		performSelectionMono = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.selection.mono");
		performSelectionMulti = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.selection.multi");
		performCaret = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.caret");
		performFocus = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform.focus");
		focusManager = Outside.service(this,"gus06.swing.textcomp.textfocus.manager");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof JTextArea)
		perform((JTextComponent) obj);
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		if(focusManager.f(comp))
		{
			performFocus.p(comp);
			return;
		}
		if(hasSelectionMulti(comp))
		{
			performSelectionMulti.p(comp);
			return;
		}
		if(hasSelection(comp))
		{
			performSelectionMono.p(comp);
			return;
		}
		performCaret.p(comp);
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