package a.entity.gus06.swing.textcomp.cust.action.ctrl_minus.decreasefontsize;

import a.framework.*;
import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractAction;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141105";}

	public static final String DEFAULT_KEY = "ctrl -";
	
	
	private Service perform;
	private Service manageKeyStroke;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_minus.decreasefontsize.perform");
		manageKeyStroke = Outside.service(this,"gus06.sys.keystroke1.manager");
	}
	
	
	public void p(Object obj) throws Exception
	{new Holder((JTextComponent) obj);}



	private class Holder extends AbstractAction
	{
		private JTextComponent comp;
		public Holder(JTextComponent comp) throws Exception
		{
			this.comp = comp;
			manageKeyStroke.p(new Object[]{id(),DEFAULT_KEY,comp,this});
		}
		public void actionPerformed(ActionEvent e)
		{perform(comp);}
	}
	
	
	private void perform(JTextComponent comp)
	{
		try{perform.p(comp);}
		catch(Exception e)
		{Outside.err(this,"perform(JTextComponent)",e);}
	}
	
	private String id()
	{return getClass().getName();}
}
