package a.entity.gus06.swing.textcomp.cust.action.alt_left.gotoleft.perform;

import a.framework.*;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140817";}

	
	private Service performSelection;
	private Service performClipboard;
	private Service performHighlight;
	private Service performCaret;

	public EntityImpl() throws Exception
	{
		performSelection = Outside.service(this,"gus06.swing.textcomp.cust.action.alt_left.gotoleft.perform.selection");
		performClipboard = Outside.service(this,"gus06.swing.textcomp.cust.action.alt_left.gotoleft.perform.clipboard");
		performHighlight = Outside.service(this,"gus06.swing.textcomp.cust.action.alt_left.gotoleft.perform.highlight");
		performCaret = Outside.service(this,"gus06.swing.textcomp.cust.action.alt_left.gotoleft.perform.caret");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof JTextArea)
		perform(obj);
	}
	
	
	private void perform(Object obj) throws Exception
	{
		if(performSelection.f(obj)) return;
		if(performHighlight.f(obj)) return;
		if(performClipboard.f(obj)) return;
		performCaret.p(obj);
	}
}