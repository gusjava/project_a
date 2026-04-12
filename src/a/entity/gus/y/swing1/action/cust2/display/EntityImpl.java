package a.entity.gus.y.swing1.action.cust2.display;

import javax.swing.Action;
import javax.swing.Icon;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;
import a.framework.V;

public class EntityImpl implements Entity, V {
	public String creationDate() {return "20240110";}

	private Service iconProvider;

	public EntityImpl() throws Exception {
		iconProvider = Outside.service(this, "gus.y.icons1.provider");
	}

	public void v(String key, Object obj) throws Exception {
		perform(key, (Action) obj);
	}

	private void perform(String info, Action action) throws Exception {
		if (info == null || info.equals("")) {
			action.putValue(Action.NAME, "");
			action.putValue(Action.SMALL_ICON, null);
		} else if (info.contains("#")) {
			String[] m = info.split("#", 2);
			action.putValue(Action.NAME, m[1]);
			action.putValue(Action.SHORT_DESCRIPTION, m[1]);
			action.putValue(Action.SMALL_ICON, icon(m[0]));
		} else {
			action.putValue(Action.NAME, info);
			action.putValue(Action.SHORT_DESCRIPTION, info);
			action.putValue(Action.SMALL_ICON, null);
		}
	}

	private Icon icon(String id) throws Exception {
		return (Icon) iconProvider.t(id);
	}
}