package a.entity.gus06.x.swing.table.cust.tooltip1;

import javax.swing.JTable;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.table.JTableHeader;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20251113";}

	public void p(Object obj) throws Exception
	{
		JTable table = (JTable) obj;
		new TooltipHandler(table);
	}

	private class TooltipHandler extends MouseMotionAdapter
	{
		private JTable table;

		public TooltipHandler(JTable table)
		{
			super();
			this.table = table;
			table.addMouseMotionListener(this);
		}

		public void mouseMoved(MouseEvent evt)
		{
			Point p = evt.getPoint();

			int x = table.rowAtPoint(p);
			int y = table.columnAtPoint(p);

			table.setToolTipText(tooltip1(x, y));

			JTableHeader header = table.getTableHeader();
			if (header != null) header.setToolTipText(tooltip2(x, y));
		}

		private String tooltip1(int x, int y)
		{
			if (x == -1) return null;
			if (y == -1) return null;
			Object value = table.getValueAt(x, y);
			return value != null ? value.toString() : null;
		}

		private String tooltip2(int x, int y)
		{
			if (x != -1) return null;
			if (y == -1) return null;
			return table.getColumnName(y);
		}
	}
}
