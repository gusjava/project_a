package a.entity.gus.y.swingactions1.alt_right.gotoright.perform;

import a.framework.*;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240121";}
	
	private Service performSelection;
	private Service performClipboard;
	private Service performHighlight;
	private Service performCaret;

	public EntityImpl() throws Exception
	{
		performSelection = Outside.service(this,"gus.y.swingactions1.alt_right.gotoright.perform.selection");
		performClipboard = Outside.service(this,"gus.y.swingactions1.alt_right.gotoright.perform.clipboard");
		performHighlight = Outside.service(this,"gus.y.swingactions1.alt_right.gotoright.perform.highlight");
		performCaret = Outside.service(this,"gus.y.swingactions1.alt_right.gotoright.perform.caret");
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