package a.entity.gus06.swing.textcomp.cust.action.ctrl_d.removeline.perform;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140816";}

	public static final String KEY_HANDLER = "ctrl_d_handler";


	private Service applyToComp;
	private Service selectionMono;
	private Service selectionMulti;
	private Service caret;


	public EntityImpl() throws Exception
	{
		applyToComp = Outside.service(this,"gus06.appli.gusexplorer.gui.editor.fillbar.applytocomp.p");
		selectionMono = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_d.removeline.perform.selection.mono");
		selectionMulti = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_d.removeline.perform.selection.multi");
		caret = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_d.removeline.perform.caret");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof JTextArea)
		perform((JTextComponent) obj);
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		boolean applied = applyToComp.f(new Object[]{comp,comp.getSelectedText(),KEY_HANDLER});
		if(applied) return;
		
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