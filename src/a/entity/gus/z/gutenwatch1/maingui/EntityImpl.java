package a.entity.gus.z.gutenwatch1.maingui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import a.entity.gus.y.gutenwatch1.store.detections.Detection;
import a.framework.E;
import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I, ActionListener {
	public String creationDate() {return "20260830";}

	public static final long LAPSE_MS = 600000L;

	public static final int COL_DETECTED = 0;
	public static final int COL_TITLE = 1;
	public static final int COL_AUTHOR = 2;
	public static final int COL_RELEASE = 3;
	public static final int COL_URL = 4;

	private static final String[] COLUMNS = {"D\u00e9tect\u00e9 le", "Titre", "Auteur", "Date de sortie", "URL"};

	private Service check;
	private Service getDetections;
	private Service timerRegister;

	private JPanel panel;
	private DefaultTableModel model;
	private JLabel status;

	public EntityImpl() throws Exception
	{
		check = Outside.service(this, "gus.y.gutenwatch1.check");
		getDetections = Outside.service(this, "gus.y.gutenwatch1.store.detections");
		timerRegister = Outside.service(this, "gus.y.timer1.register");

		model = new DefaultTableModel(COLUMNS, 0) {
			public boolean isCellEditable(int row, int col) {return false;}
		};

		final JTable table = new JTable(model);
		table.setCellSelectionEnabled(true);

		fixFixedWidthColumn(table, COL_DETECTED, "0000-00-00 00:00:00");
		fixFixedWidthColumn(table, COL_RELEASE, "Sep 30, 2026");

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) openSelectedUrl(table);
			}
		});

		table.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK), "openUrl");
		table.getActionMap().put("openUrl", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedColumn() == COL_URL) openSelectedUrl(table);
			}
		});

		status = new JLabel("En attente de la premi\u00e8re v\u00e9rification...");

		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		panel.add(status, BorderLayout.SOUTH);

		check.addActionListener(this);

		refresh();

		timerRegister.v("" + LAPSE_MS, (E) this::checkNow);
	}

	public Object i() throws Exception
	{
		return panel;
	}

	public void actionPerformed(ActionEvent e)
	{
		refresh();
	}

	private void checkNow()
	{
		try {check.e();}
		catch (Exception e) {Outside.err(this, "checkNow()", e);}
	}

	private void refresh()
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {doRefresh();}
		});
	}

	private void doRefresh()
	{
		try {
			List<Detection> detections = (List<Detection>) getDetections.g();
			model.setRowCount(0);
			for (Detection d : detections) {
				model.addRow(new Object[] {d.detectedAt, d.entry.title, d.entry.author, d.entry.releaseDate, d.entry.url});
			}
			status.setText(detections.size() + " entr\u00e9e(s) - actualis\u00e9 le " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		}
		catch (Exception e) {Outside.err(this, "doRefresh()", e);}
	}

	private void openSelectedUrl(JTable table)
	{
		try {
			int row = table.getSelectedRow();
			if (row < 0) return;
			String url = (String) model.getValueAt(row, COL_URL);
			Desktop.getDesktop().browse(new URI(url));
		}
		catch (Exception e) {Outside.err(this, "openSelectedUrl(JTable)", e);}
	}

	private void fixFixedWidthColumn(JTable table, int columnIndex, String sample)
	{
		TableColumn column = table.getColumnModel().getColumn(columnIndex);
		FontMetrics fm = table.getFontMetrics(table.getFont());
		FontMetrics headerFm = table.getTableHeader().getFontMetrics(table.getTableHeader().getFont());
		int width = Math.max(fm.stringWidth(sample), headerFm.stringWidth(String.valueOf(column.getHeaderValue())));
		width += 20;
		column.setMinWidth(width);
		column.setMaxWidth(width);
		column.setPreferredWidth(width);
		column.setResizable(false);
	}
}
