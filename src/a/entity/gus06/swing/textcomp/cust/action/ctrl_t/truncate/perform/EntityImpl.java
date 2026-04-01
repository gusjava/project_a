package a.entity.gus06.swing.textcomp.cust.action.ctrl_t.truncate.perform;

import a.framework.*;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141114";}


	private Service performFocus;
	private Service focusManager;


	public EntityImpl() throws Exception
	{
		performFocus = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_t.truncate.perform.focus");
		focusManager = Outside.service(this,"gus06.swing.textcomp.textfocus.manager");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof JTextArea)
		perform((JTextComponent) obj);
	}
	
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		if(focusManager.f(comp))
		{
			performFocus.p(comp);
			return;
		}
		
		int p = comp.getCaretPosition();
		String text = comp.getText(0,p);
		comp.setText(text);
	}
}
