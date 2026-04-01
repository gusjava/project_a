package a.entity.gus06.y.entitysys1.perform.entity.delete.ask;

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

	public String creationDate() {return "20251113";}

	public static final String TITLE = "Entity deletion";
	public static final String MESSAGE1 = "You are not allowed to delete entity: ";
	public static final String MESSAGE2 = "Please, confirm deletion for entity: ";

	public static final String TITLE_DEPENDENCIES = "Entity's dependencies";

	private Service perform;
	private Service findDownLinks;
	

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.y.entitysys1.perform.entity.delete");
		findDownLinks = Outside.service(this, "gus.y.entitydb1.entity_link.find2");
	}

	public void p(Object obj) throws Exception
	{f(obj);}

	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 3) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String entityName = (String) o[1];
		Object anchor = o[2];

		Window window = SwingUtilities.getWindowAncestor((Component) anchor);
		
		String devId = (String) ((R) engine).r("devId");
		if (devId != null && !entityName.startsWith(devId + "."))
		{
			String message = MESSAGE1 + "\n" + entityName;
			JOptionPane.showMessageDialog(window, message, TITLE, JOptionPane.ERROR_MESSAGE);
			return false;
		}

		{
			String message = MESSAGE2 + "\n" + entityName;
			int r = JOptionPane.showConfirmDialog(window, message, TITLE, JOptionPane.YES_NO_OPTION);
			if (r != JOptionPane.YES_OPTION) return false;
		}
		// find dependencies

		Connection cx = (Connection) ((R) engine).r("cx");
		Set downLinks = (Set) findDownLinks.t(new Object[] { cx, entityName });

		if (!downLinks.isEmpty())
		{
			String message = "The entity " + entityName + " is used by " + downLinks.size() + " other entities:\n"
					+ toString(downLinks) + "\nAre you really sure you want to delete this entity ?";
			int r = JOptionPane.showConfirmDialog(window, message, TITLE_DEPENDENCIES, JOptionPane.YES_NO_OPTION);
			if (r != JOptionPane.YES_OPTION) return false;
		}

		// delete entity

		return perform.f(new Object[] { engine, entityName });
	}

	private String toString(Set links)
	{
		StringBuffer b = new StringBuffer();
		List list = new ArrayList(links);
		Collections.sort(list);
		int nb = Math.min(list.size(), 10);
		for (int i = 0; i < nb; i++) b.append("- " + list.get(i) + "\n");
		if (nb < list.size()) b.append("- ...\n");
		return b.toString();
	}
}
