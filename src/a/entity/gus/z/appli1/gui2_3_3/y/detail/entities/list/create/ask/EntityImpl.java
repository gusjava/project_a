package a.entity.gus.z.appli1.gui2_3_3.y.detail.entities.list.create.ask;

import java.awt.Component;
import java.awt.Window;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20260826";}

	public static final String TITLE = "Entity creation";
	public static final String MESSAGE = "Please, enter entity's generation rule (relative to ";

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
		if (o.length != 3) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String yPrefix = (String) o[1];
		Object anchor = o[2];

		Window window = SwingUtilities.getWindowAncestor((Component) anchor);
		String rule = (String) JOptionPane.showInputDialog(
			window,
			MESSAGE + yPrefix + "):",
			TITLE,
			JOptionPane.PLAIN_MESSAGE,
			null,
			null,
			null
		);
		if (rule == null || rule.trim().equals("")) return false;

		String fullRule = yPrefix + "." + rule.trim();

		String errMsg = (String) perform.t(new Object[] { engine, fullRule });
		if (errMsg != null)
		{
			JOptionPane.showMessageDialog(window, errMsg, TITLE, JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		return true;
	}
}
