package a.entity.gus06.swing.textfield.cust.action.ctrl_c.copy.perform;

import a.framework.*;
import javax.swing.JTextField;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220909";}


	private Service clipboardAccess;

	public EntityImpl() throws Exception
	{
		clipboardAccess = Outside.service(this,"gus06.clipboard.access.string");
	}
	
	public void p(Object obj) throws Exception
	{
		perform((JTextField) obj);
	}
	
	private void perform(JTextField comp) throws Exception
	{
		String selection = comp.getSelectedText();
		if(selection!=null && !selection.equals(""))
			clipboardAccess.p(selection);
		else clipboardAccess.p(comp.getText());
	}
}
