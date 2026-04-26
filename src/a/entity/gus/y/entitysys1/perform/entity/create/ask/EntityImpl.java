package a.entity.gus.y.entitysys1.perform.entity.create.ask;

import java.awt.Component;
import java.awt.Window;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import a.framework.Entity;
import a.framework.F;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20240116";}

	public static final String TITLE = "Entity creation";
	public static final String MESSAGE = "Please, enter entity's generation rule:";

	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.y.entitysys1.perform.entity.create");
	}

	public void p(Object obj) throws Exception
	{f(obj);}

	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length == 2) return handle(o[0], o[1], null);
		if(o.length == 3) return handle(o[0], o[1], (String) o[2]);
		
		throw new Exception("Wrong data number: " + o.length);
	}
	
	private boolean handle(Object engine, Object anchor, String initValue) throws Exception
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

		String errMsg = (String) perform.t(new Object[] { engine, rule });
		if(errMsg!=null)
		{
			JOptionPane.showMessageDialog(window, errMsg, TITLE, JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		return true;
	}
}
