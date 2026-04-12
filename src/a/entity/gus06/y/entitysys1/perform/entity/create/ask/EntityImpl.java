package a.entity.gus06.y.entitysys1.perform.entity.create.ask;

import java.awt.Component;
import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import a.framework.*;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20251113";}

	public static final String TITLE = "Entity creation";
	public static final String MESSAGE = "Please, enter entity's generation rule:";
	public static final String MESSAGE_ERR = "Entity generation has been aborted";

	private Service perform;
	private Service validate;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.y.entitysys1.perform.entity.create");
		validate = Outside.service(this, "gus.x.entity.name.validate");
	}

	public void p(Object obj) throws Exception
	{f(obj);}

	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length == 2) return handle(o[0], o[1], null);
		if (o.length == 3) return handle(o[0], o[1], (String) o[2]);
		throw new Exception("Wrong data number: " + o.length);
	}
	
	private boolean handle(Object engine1, Object anchor, String initValue) throws Exception
	{
		Window window = SwingUtilities.getWindowAncestor((Component) anchor);
		
		String rule = (String) JOptionPane.showInputDialog(
			window, 
			MESSAGE, 
			TITLE, 
			JOptionPane.PLAIN_MESSAGE,
			null,
			null,
			initValue
		);
		if (rule == null || rule.trim().equals("")) return false;

		boolean done = perform.f(new Object[] { engine1, rule });
		if (!done)
		{
			JOptionPane.showMessageDialog(window, MESSAGE_ERR, TITLE, JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		return true;
	}
}
