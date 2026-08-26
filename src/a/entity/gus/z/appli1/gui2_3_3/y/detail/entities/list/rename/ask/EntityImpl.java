package a.entity.gus.z.appli1.gui2_3_3.y.detail.entities.list.rename.ask;

import java.awt.Component;
import java.awt.Window;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20260826";}

	public static final String TITLE = "Entity rename";
	public static final String MESSAGE = "Please, enter entity's new name (relative to ";

	public static final String TITLE_DEPENDENCIES = "Entity's dependencies";

	private Service perform;
	private Service findDownLinks;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.y.entitysys1.perform.entity.rename");
		findDownLinks = Outside.service(this, "gus.y.entitydb1.entity_link.find2");
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

		Connection cx = (Connection) ((R) engine).r("cx");
		Set downLinks = (Set) findDownLinks.t(new Object[] { cx, oldName });
		boolean refactor = false;

		if (!downLinks.isEmpty())
		{
			String message = "The entity " + oldName + " is used by " + downLinks.size() + " other entities:\n"
				+ toString(downLinks) + "\nWould you like to update these links with the new name ?";
			int r = JOptionPane.showConfirmDialog(window, message, TITLE_DEPENDENCIES,
				JOptionPane.YES_NO_CANCEL_OPTION);
			if (r == JOptionPane.CANCEL_OPTION) return false;
			refactor = r == JOptionPane.YES_OPTION;
		}

		String errMsg = (String) perform.t(new Object[] { engine, oldName, newName, refactor });
		if (errMsg != null)
		{
			JOptionPane.showMessageDialog(window, errMsg, TITLE, JOptionPane.PLAIN_MESSAGE);
			return false;
		}
		return true;
	}

	private String toString(Set links)
	{
		StringBuffer b = new StringBuffer();
		List list = new ArrayList(links);
		Collections.sort(list);
		int nb = Math.min(list.size(), 10);
		for (int i = 0; i < nb; i++)
			b.append("- " + list.get(i) + "\n");
		if (nb < list.size())
			b.append("- ...\n");
		return b.toString();
	}
}
