package a.entity.gus06.y.entityeditor1.gui2.err;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import a.framework.*;

public class EntityImpl implements Entity, I, P, ActionListener {

	public String creationDate() {return "20251121";}
	
	public static final String COL_ID = "id";
	public static final String COL_ENTITY_NAME = "entity_name";
	public static final String COL_FILE_NAME = "file_name";
	public static final String COL_DATE = "date";
	public static final String COL_LINE = "line";
	public static final String COL_LINE_NB = "line_nb";
	public static final String COL_LINE_POS = "line_pos";
	public static final String COL_TYPE = "type";
	public static final String COL_DESCRIPTION = "description";
	
	private JPanel panel;
	private JTable table;
	private JScrollPane scroll;
	private TableModel0 model;
	private JLabel numberLabel;

	private Object data;
	private List compileErrList;

	public EntityImpl() throws Exception
	{
		model = new TableModel0();
		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		table.setGridColor(Color.WHITE);

		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);

		numberLabel = new JLabel(" 0");

		panel = new JPanel(new BorderLayout());
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(numberLabel, BorderLayout.SOUTH);
		
		initColumnSize(1, 80);
	}

	public Object i() throws Exception
	{return panel;}

	private void initColumnSize(int index, int size)
	{
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(size);
		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(size);
	}

	public void p(Object obj) throws Exception
	{
		if (data != null)((S) data).removeActionListener(this);
		if (obj == null) {reset();return;}

		data = obj;
		compileErrList = (List) ((R) data).r("compileErrList");
		numberLabel.setText(" " + compileErrList.size());
		model.fireTableDataChanged();
		((S) data).addActionListener(this);
	}

	private void reset()
	{
		data = null;
		compileErrList = null;

		numberLabel.setText(" 0");
		model.fireTableDataChanged();
	}

	private class TableModel0 extends AbstractTableModel {
		public int getColumnCount()
		{return 4;}

		public int getRowCount()
		{return getErrNumber();}

		public Class getColumnClass(int y)
		{return String.class;}

		public String getColumnName(int y)
		{
			if (y == 0) return "file name";
			if (y == 1) return "position";
			if (y == 2) return "description";
			if (y == 3) return "line";
			return null;
		}

		public boolean isCellEditable(int x, int y)
		{return false;}

		public Object getValueAt(int x, int y)
		{
			if (compileErrList == null) return "";
			Map info = (Map) compileErrList.get(x);

			if (y == 0) return info.get(COL_FILE_NAME);
			if (y == 1) return info.get(COL_LINE_NB) + ":" + info.get(COL_LINE_POS);
			if (y == 2) return info.get(COL_DESCRIPTION);
			if (y == 3) return info.get(COL_LINE);
			return null;
		}
	}

	private int getErrNumber()
	{return compileErrList != null ? compileErrList.size() : 0;}

	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if (s.equals("srcModified()")){srcModified();return;}
	}

	private void srcModified()
	{
		try
		{
			compileErrList = (List) ((R) data).r("compileErrList");
			numberLabel.setText(" " + compileErrList.size());
			model.fireTableDataChanged();
		}
		catch(Exception e)
		{Outside.err(this, "srcModified()", e);}
	}
}
