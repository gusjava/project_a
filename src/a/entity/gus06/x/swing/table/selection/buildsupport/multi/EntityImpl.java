package a.entity.gus06.x.swing.table.selection.buildsupport.multi;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import a.framework.*;
import java.util.Arrays;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251128";}

	public Object t(Object obj) throws Exception
	{return new Support((JTable) obj);}

	private class Support extends S1 implements ListSelectionListener
	{
		private JTable table;
		private int[] selectedRows;

		public Support(JTable table)
		{
			this.table = table;
			selectedRows = table.getSelectedRows();
			table.getSelectionModel().addListSelectionListener(this);
		}

		public void valueChanged(ListSelectionEvent e)
		{
			if (e.getValueIsAdjusting()) return;

			int[] newSelectedRows = table.getSelectedRows();
			if (!Arrays.equals(selectedRows, newSelectedRows))
			{
				selectionChanged();
				selectedRows = newSelectedRows;
			}
		}

		private void selectionChanged()
		{send(this, "selectionChanged()");}
	}
}