package a.entity.gus.y.swingactions1.ctrl_x.cut.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240714";}

	private Service enlargeSelection;

	public EntityImpl() throws Exception {
		enlargeSelection = Outside.service(this,"gus.y.swingactions1.ctrl_space.enlargeselection.perform");
	}
	
	public void p(Object obj) throws Exception {
		perform((JTextComponent) obj);
	}
	
	private void perform(JTextComponent comp) throws Exception {
		String selection = comp.getSelectedText();
		if(selection==null || selection.equals(""))
			enlargeSelection.p(comp);
		comp.cut();
	}
}