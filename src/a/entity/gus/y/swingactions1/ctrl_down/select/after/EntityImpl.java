package a.entity.gus.y.swingactions1.ctrl_down.select.after;

import a.framework.*;
import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240120";}

	public static final String KEY = "ctrl down";
	
	private Service perform;
	private Service stringToKeyStroke;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus.y.swingactions1.ctrl_down.select.after.perform");
		stringToKeyStroke = Outside.service(this,"gus.y.convert1.stringtokeystroke");
	}
	
	public void p(Object obj) throws Exception
	{new Holder((JTextComponent) obj);}

	private class Holder extends AbstractAction implements R {
		private JTextComponent comp;
		private KeyStroke keyStroke;
		
		public Holder(JTextComponent comp) throws Exception
		{
			this.comp = comp;
			keyStroke = (KeyStroke) stringToKeyStroke.t(KEY);
			comp.getInputMap().put(keyStroke, this);
		}
		public void actionPerformed(ActionEvent e)
		{perform(comp);}

		public Object r(String key) throws Exception {
			if (key.equals("comp"))
				return comp;
			if (key.equals("keyStroke"))
				return keyStroke;
			if (key.equals("keys"))
				return new String[] { "comp", "keyStroke" };

			throw new Exception("Unknown key: " + key);
		}
	}
	
	private void perform(JTextComponent comp)
	{
		try{perform.p(comp);}
		catch(Exception e)
		{Outside.err(this,"perform(JTextComponent)",e);}
	}
}
