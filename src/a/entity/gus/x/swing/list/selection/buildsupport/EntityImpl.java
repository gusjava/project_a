package a.entity.gus.x.swing.list.selection.buildsupport;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a.framework.Entity;
import a.framework.S1;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240125";}

	public Object t(Object obj) throws Exception {
		return new Support((JList) obj);
	}

	private class Support extends S1 implements ListSelectionListener {
		private JList list;
		private int selectedIndex;

		public Support(JList list) {
			this.list = list;
			selectedIndex = list.getSelectedIndex();
			list.getSelectionModel().addListSelectionListener(this);
		}

		public void valueChanged(ListSelectionEvent e) {
			int newSelectedIndex = list.getSelectedIndex();
			if (selectedIndex != newSelectedIndex)
				selectionChanged();
			selectedIndex = newSelectedIndex;
		}

		private void selectionChanged() {
			send(this, "selectionChanged()");
		}
	}
}
