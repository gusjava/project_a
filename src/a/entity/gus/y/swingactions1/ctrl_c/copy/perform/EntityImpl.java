package a.entity.gus.y.swingactions1.ctrl_c.copy.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240120";}

	private Service enlargeSelection;
	private Service clipboardAccess;

	public EntityImpl() throws Exception {
		enlargeSelection = Outside.service(this,"gus.y.swingactions1.ctrl_space.enlargeselection.perform");
		clipboardAccess = Outside.service(this,"gus.x.clipboard.string");
	}
	
	public void p(Object obj) throws Exception {
		perform((JTextComponent) obj);
	}
	
	private void perform(JTextComponent comp) throws Exception {
		String selection = comp.getSelectedText();
		if(selection!=null && !selection.equals(""))
		{
			String content = (String) clipboardAccess.g();
			if(content!=null && content.equals(selection)) enlargeSelection.p(comp);
			
			perform2(comp);
			return;
		}
		enlargeSelection.p(comp);
		perform2(comp);
	}
	
	private void perform2(JTextComponent comp) throws Exception {
		String selection = comp.getSelectedText();
		clipboardAccess.p(selection);
	}
}