package a.entity.gus06.swing.textcomp.cust.action.ctrl_p.wrapline.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140817";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof JTextArea)
		perform((JTextArea) obj);
	}
	
	
	
	
	private void perform(JTextArea area) throws Exception
	{
		int p = area.getCaretPosition();
		
		boolean val = !area.getLineWrap();
		area.setLineWrap(val);
            
		area.requestFocus();
		area.setCaretPosition(p);
	}
}
