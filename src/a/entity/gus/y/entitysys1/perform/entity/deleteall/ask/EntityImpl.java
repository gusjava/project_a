package a.entity.gus.y.entitysys1.perform.entity.deleteall.ask;

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

	public String creationDate() {return "20260416";}

	public static final String TITLE = "Entity deletion";
	public static final String MESSAGE1 = "You are not allowed to delete entities: ";
	public static final String MESSAGE2 = "Please, confirm deletion for entities: ";

	public static final String TITLE_DEPENDENCIES = "Entity's dependencies";

	private Service perform;
	private Service findDownLinks;
	

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.y.entitysys1.perform.entity.deleteall");
		findDownLinks = Outside.service(this, "gus.y.entitydb1.entity_link.find2");
	}

	public void p(Object obj) throws Exception
	{f(obj);}

	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 3) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		List entityNames = (List) o[1];
		Object anchor = o[2];

		Window window = SwingUtilities.getWindowAncestor((Component) anchor);
		
		String devId = (String) ((R) engine).r("devId");
		if (devId != null)
		{
			List otherNames = findOtherNames(entityNames, devId);
			if(otherNames.size()>0)
			{
				StringBuilder sb = new StringBuilder(MESSAGE1 + "\n");
				for(int i=0;i<otherNames.size();i++) sb.append(otherNames.get(i)+"\n");
				JOptionPane.showMessageDialog(window, sb.toString(), TITLE, JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}

		{
			StringBuilder sb = new StringBuilder(MESSAGE2 + "\n");
			for(int i=0;i<entityNames.size();i++) sb.append(entityNames.get(i)+"\n");
			int r = JOptionPane.showConfirmDialog(window, sb.toString(), TITLE, JOptionPane.YES_NO_OPTION);
			if (r != JOptionPane.YES_OPTION) return false;
		}
		// find dependencies

		Connection cx = (Connection) ((R) engine).r("cx");
		
		List usedNames = new ArrayList();
		for(int i=0;i<entityNames.size();i++)
		{
			String entityName = (String) entityNames.get(i);
			Set downLinks = (Set) findDownLinks.t(new Object[] { cx, entityName });
			if (downLinks.size()>0) usedNames.add(entityName);
		}
		if(usedNames.size()>0)
		{
			StringBuilder sb = new StringBuilder("The entities:\n");
			for(int i=0;i<usedNames.size();i++) sb.append(usedNames.get(i)+"\n");
			sb.append("are used ny other entities.\nAre you really sure you want to delete theses entities ?");
			
			int r = JOptionPane.showConfirmDialog(window, sb.toString(), TITLE_DEPENDENCIES, JOptionPane.YES_NO_OPTION);
			if (r != JOptionPane.YES_OPTION) return false;
		}

		// delete entity

		return perform.f(new Object[] { engine, entityNames });
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
	
	private List findOtherNames(List entityNames, String devId)
	{
		List list = new ArrayList();
		for(int i=0;i<entityNames.size();i++)
		{
			String entityName = (String) entityNames.get(i);
			if(!entityName.startsWith(devId + ".")) list.add(entityName);
		}
		return list;
	}
}
