package a.entity.gus.y.swingactions1.ctrl_t.truncate.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240121";}

	public void p(Object obj) throws Exception
	{
		perform((JTextComponent) obj);
	}
	
	private void perform(JTextComponent comp) throws Exception
	{
		int p = comp.getCaretPosition();
		String text = comp.getText(0,p);
		comp.setText(text);
	}
}
