package a.entity.gus.z.appli1.gui2_3_3.y.detail.entities.list.duplicate.ask;

import java.awt.Component;
import java.awt.Window;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20260826";}

	public static final String TITLE = "Entity duplication";
	public static final String MESSAGE = "Please, enter entity's new name (relative to ";

	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.y.entitysys1.perform.entity.duplicate");
	}

	public void p(Object obj) throws Exception
	{f(obj);}

	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 4) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String yPrefix = (String) o[1];
		String oldName = (String) o[2];
		Object anchor = o[3];

		String oldShortName = oldName.startsWith(yPrefix + ".") ? oldName.substring(yPrefix.length() + 1) : oldName;

		Window window = SwingUtilities.getWindowAncestor((Component) anchor);
		String newShortName = (String) JOptionPane.showInputDialog(window, MESSAGE + yPrefix + "):", TITLE,
			JOptionPane.PLAIN_MESSAGE, null, null, oldShortName);
		if (newShortName == null || newShortName.trim().equals("") || newShortName.equals(oldShortName))
			return false;

		String newName = yPrefix + "." + newShortName.trim();

		String errMsg = (String) perform.t(new Object[] { engine, oldName, newName });
		if (errMsg != null)
		{
			JOptionPane.showMessageDialog(window, errMsg, TITLE, JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		return true;
	}
}
