package a.entity.gus06.swing.textcomp.cust.putaction;

import a.framework.*;

import java.awt.Event;
import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140727";}

	private Service findKeystroke;
	private Service findAction;

	public EntityImpl() throws Exception
	{
		findKeystroke = Outside.service(this,"gus06.find.keystroke");
		findAction = Outside.service(this,"gus06.find.action");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);

		JTextComponent comp = (JTextComponent) o[0];
		Action action = (Action) findAction.t(o[1]);
		KeyStroke key = (KeyStroke) findKeystroke.t(o[2]);

		comp.getInputMap().put(key,action);
	}
}
