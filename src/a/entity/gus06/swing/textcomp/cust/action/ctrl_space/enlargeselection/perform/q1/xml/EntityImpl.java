package a.entity.gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform.q1.xml;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160426";}


	private Service goForward;
	private Service goBack;
	private Service performZ1;
	
	public EntityImpl() throws Exception
	{
		goForward = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform.q1.xml.goforward");
		goBack = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform.q1.xml.goback");
		performZ1 = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_space.enlargeselection.perform.q2");
	}


	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String s = comp.getSelectedText();
		
		if(!s.startsWith("<") || !s.endsWith(">"))
			throw new Exception("Invalid xml tag: "+s);
		
		String s0 = s.substring(1,s.length()-1).trim();
		
		if(s0.charAt(0)=='/')
		{
			String name = s0.substring(1).trim();
			goBack.p(new Object[]{comp,name});
		}
		else if(s0.endsWith("/"))
		{
			performZ1.p(comp);
			return;
		}
		else
		{
			String name = s0.split("[ \\t\\n]")[0];
			goForward.p(new Object[]{comp,name});
		}
	}
}