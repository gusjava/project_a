package a.entity.gus.x.swing.table.selection.buildsupport;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import a.framework.Entity;
import a.framework.S1;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240114";}

	public Object t(Object obj) throws Exception {
		return new Support((JTable) obj);
	}

	private class Support extends S1 implements ListSelectionListener {
		private JTable table;
		private int selectedRow;

		public Support(JTable table) {
			this.table = table;
			selectedRow = table.getSelectedRow();
			table.getSelectionModel().addListSelectionListener(this);
		}

		public void valueChanged(ListSelectionEvent e) {
			int newSelectedRow = table.getSelectedRow();
			if (selectedRow != newSelectedRow)
				selectionChanged();
			selectedRow = newSelectedRow;
		}

		private void selectionChanged() {
			send(this, "selectionChanged()");
		}
	}
}
