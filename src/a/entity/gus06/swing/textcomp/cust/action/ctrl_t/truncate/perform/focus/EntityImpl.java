package a.entity.gus06.swing.textcomp.cust.action.ctrl_t.truncate.perform.focus;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160827";}
	
	
	private Service handleRange;

	public EntityImpl() throws Exception
	{
		handleRange = Outside.service(this,"gus06.swing.textcomp.textfocus.handlerange");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		int start = 0;
		int end = comp.getCaretPosition();
		
		int[] range = new int[]{start,end};
		handleRange.p(new Object[]{comp,range});
	}
}
