package a.entity.gus06.swing.textfield.cust.action.ctrl_q.keepselection.perform;

import a.framework.*;
import javax.swing.JTextField;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220909";}
	
	public void p(Object obj) throws Exception
	{
		perform((JTextField) obj);
	}
	
	private void perform(JTextField comp) throws Exception
	{
		String s = comp.getSelectedText();
		comp.setText(s!=null && !s.equals("") ? s : "");
	}
}