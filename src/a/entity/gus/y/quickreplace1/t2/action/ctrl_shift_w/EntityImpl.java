package a.entity.gus.y.quickreplace1.t2.action.ctrl_shift_w;

import a.framework.*;
import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240715";}

	public static final String KEY = "ctrl shift w";
	
	private Service buildHolder;
	private Service buildTransform;
	private Service stringToKeyStroke;
	
	public EntityImpl() throws Exception
	{
		buildHolder = Outside.service(this,"gus.y.quickreplace1.holder.find");
		buildTransform = Outside.service(this,"gus.y.quickreplace1.t2");
		stringToKeyStroke = Outside.service(this,"gus.y.convert1.stringtokeystroke");
	}
	
	public void p(Object obj) throws Exception
	{new Holder((JTextComponent) obj);}

	private class Holder extends AbstractAction implements R {
		private JTextComponent comp;
		private KeyStroke keyStroke;
		private P holder;
		
		public Holder(JTextComponent comp) throws Exception
		{
			this.comp = comp;
			holder = (P) buildHolder.t(comp);
			keyStroke = (KeyStroke) stringToKeyStroke.t(KEY);
			comp.getInputMap().put(keyStroke, this);
		}
		public void actionPerformed(ActionEvent e)
		{perform(holder);}

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
	
	private void perform(P holder)
	{
		try{holder.p(buildTransform);}
		catch(Exception e)
		{Outside.err(this,"perform(P)",e);}
	}
}
