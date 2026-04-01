package a.entity.gus06.data.editor.string2.editor1;

import a.framework.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class EntityImpl extends S1 implements Entity, I, P, G, V, TableModelListener
{
	public String creationDate() {return "20150702";}


	private Service columnsToClipboard;
	private Service tableTooltip;
	private Service modelToData;
	private Service posLabelBuilder;
	
	private DefaultTableModel model;
	private boolean editable = false;
	private JTable table;
	private JLabel label_info;
	private JLabel label_pos;
	private JButton button_columnsToClipboard;
	private JPanel panel;
	
	
	
	public EntityImpl() throws Exception
	{
		columnsToClipboard = Outside.service(this,"gus06.data.string2.columnstoclipboard");
		tableTooltip = Outside.service(this,"gus06.swing.table.cust.tooltip1");
		modelToData = Outside.service(this,"gus06.find.stringtable");
		posLabelBuilder = Outside.service(this,"gus06.swing.table.buildlabel.cellposition");
		
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {return editable;}
		};
		model.addTableModelListener(this);
		
		table = new JTable(model);
		table.setCellSelectionEnabled(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		
		table.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent e)
			{
				if(!editable) return;
				int x = table.getSelectedRow();
				int y = table.getSelectedColumn();
				if(e.getKeyCode()==KeyEvent.VK_DELETE)
				table.setValueAt("",x,y);
			}
		});
		
		tableTooltip.p(table);
		
		label_info = new JLabel(" ");
		label_pos = (JLabel) posLabelBuilder.t(table);
		
		button_columnsToClipboard = new JButton("columns to clipboard");
		button_columnsToClipboard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{columnsToClipboard();}
		});
		
		JToolBar bar = new JToolBar();
		bar.setFloatable(false);
		bar.add(label_info);
		bar.addSeparator();
		bar.add(label_pos);
		bar.addSeparator();
		bar.add(button_columnsToClipboard);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
		panel.add(bar,BorderLayout.SOUTH);
	}
	
	
	
	public Object g() throws Exception
	{return retrieveTab();}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	private String[][] retrieveTab() throws Exception
	{return (String[][]) modelToData.t(model);}
	
	
	


	private void columnsToClipboard()
	{
		try
		{
			String[][] tab = retrieveTab();
			columnsToClipboard.p(tab);
		}
		catch(Exception e)
		{Outside.err(this,"columnsToClipboard()",e);}
	}


	

	public void p(Object obj) throws Exception
	{
		String[][] tab = (String[][]) obj;
		
		setActivated(false);
		updateModel(tab);
		setActivated(true);
		label_info.setText(" ["+model.getRowCount()+","+model.getColumnCount()+"]");
	}
	
	
	
	
	
	
	private void updateModel(String[][] tab)
	{
		if(tab.length!=0)
		{
			String[] columns = new String[tab[0].length];
			model.setDataVector(tab,columns);
		}
		else model.setDataVector(new Vector(),new Vector());
	}


	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("editable"))
		{
			editable = obj.equals("true");
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void tableChanged(TableModelEvent e)
	{dataModified();}
	
	private void dataModified()
	{send(this,"dataModified()");}
}