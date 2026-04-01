package a.entity.gus06.swing.textfield.cust.action.ctrl_x.cut.perform;

import a.framework.*;
import javax.swing.JTextField;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220909";}


	public EntityImpl() throws Exception
	{
	}
	
	public void p(Object obj) throws Exception
	{
		perform((JTextField) obj);
	}
	
	private void perform(JTextField comp) throws Exception
	{
		if(!hasSelection(comp)) 
			comp.selectAll();
		comp.cut();
	}
	
	private boolean hasSelection(JTextField comp)
	{
		String s = comp.getSelectedText();
		return s!=null && !s.equals("");
	}
}
