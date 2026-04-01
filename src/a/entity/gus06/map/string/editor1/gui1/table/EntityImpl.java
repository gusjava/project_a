package a.entity.gus06.map.string.editor1.gui1.table;

import a.framework.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.table.AbstractTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import java.util.HashMap;

public class EntityImpl extends S1 implements Entity, I, P, R, ActionListener {

	public String creationDate() {return "20140831";}


	private Service performCtrlB;
	private Service clipboard;

	private Map map;
	
	private TableModel0 model;
	private JTable table;
	private JScrollPane scroll;
	private String editedKey;
	

	public EntityImpl() throws Exception
	{
		performCtrlB = Outside.service(this,"gus06.map.string.editor1.perform.ctrl_b");
		clipboard = Outside.service(this,"gus06.sys.clipboard1.g.string.filepath");
		
		model = new TableModel0();
		table = new JTable(model);
		scroll = new JScrollPane(table);
		
		table.setCellSelectionEnabled(true);
		table.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e)
			{
				if(table.getSelectionModel().isSelectionEmpty())return;
				
				boolean isCtrl = (e.getModifiers() & KeyEvent.CTRL_MASK) != 0;
				
				if(e.getKeyCode()==KeyEvent.VK_DELETE) deleteFromJTable();
				if(e.getKeyCode()==KeyEvent.VK_ENTER) editFromJTable();
				
				if(isCtrl && e.getKeyCode()==KeyEvent.VK_B) ctrlB();
				if(isCtrl && e.getKeyCode()==KeyEvent.VK_V) ctrlVFromJTable();
			}
		});
	}
	
	
	public Object i() throws Exception
	{return scroll;}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(map!=null) ((S) map).removeActionListener(this);
		map = (Map) obj;
		if(map!=null) ((S) map).addActionListener(this);
		
		SwingUtilities.invokeLater(model);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("editedKey")) return editedKey;
		if(key.equals("keys")) return new String[]{"editedKey"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void actionPerformed(ActionEvent evt)
	{SwingUtilities.invokeLater(model);}
	
	
	
	private class TableModel0 extends AbstractTableModel implements Runnable
	{
		private volatile ArrayList keys;
        
		public TableModel0()
		{keys = new ArrayList();}
    	
		public int getRowCount(){return keys.size();}
		public int getColumnCount(){return 2;}
    	
		public boolean isCellEditable(int x, int y){return true;}
		public Class getColumnClass(int y){return String.class;}
		public String getColumnName(int y){return y==0?"key":"value";}
    	
		public Object getValueAt(int x, int y)
		{
			Object key = keys.get(x);
			if(y==0)return key;
			return map!=null ? map.get(key) : null;
		}
    	
		public void setValueAt(Object value, int x, int y)
		{
			if(y==0)modifyKeyAt(x,value);
			if(y==1)modifyValueAt(x,value);
		}

		private void modifyKeyAt(int index, Object newKey)
		{
			Object key = keys.get(index);
			Object value = map.get(key);
			map.remove(key);
			map.put(newKey,value);
			SwingUtilities.invokeLater(this);
		}

		private void modifyValueAt(int index, Object newValue)
		{
			Object key = keys.get(index);
			map.put(key,newValue);
			SwingUtilities.invokeLater(this);
		}

		public void run()
		{
			keys = map!=null?new ArrayList(map.keySet()):new ArrayList();
			Collections.sort(keys);
			fireTableDataChanged();
		}
	}
	
	
	private String[] getSelectedKeys()
	{
		int[] row = table.getSelectedRows();
		String[] sKeys = new String[row.length];
		for(int i=0;i<row.length;i++)
		sKeys[i] = (String) table.getValueAt(row[i],0);
		return sKeys;
	}
	
	private String[] getSelectedValues()
	{
		int[] row = table.getSelectedRows();
		String[] sValues = new String[row.length];
		for(int i=0;i<row.length;i++)
		sValues[i] = (String) table.getValueAt(row[i],1);
		return sValues;
	}
	
	private Map getSelectedMap()
	{
		int[] row = table.getSelectedRows();
		Map m = new HashMap();
		for(int i=0;i<row.length;i++)
		{
			String key = (String) table.getValueAt(row[i],0);
			String value = (String) table.getValueAt(row[i],1);
			m.put(key,value);
		}
		return m;
	}
	
	
	private void deleteFromJTable()
	{
		String[] sKeys = getSelectedKeys();
		SwingUtilities.invokeLater(new DeleteHolder(sKeys));
	}
	
	
	private void editFromJTable()
	{
		int[] columns = table.getSelectedColumns();
		if(columns.length!=1) return;
		
		int[] row = table.getSelectedRows();
		if(row.length!=1) return;
		
		if(columns[0]!=1) return;
		editedKey = (String) table.getValueAt(row[0],0);
		editionTriggered();
	}
	
	
	private void ctrlVFromJTable()
	{
		try
		{
			String s = (String) clipboard.g();
			if(s==null) return;
			
			int[] columns = table.getSelectedColumns();
			if(columns.length!=1) return;
			boolean isKey = columns[0]==0;
			if(isKey && map.containsKey(s)) return;
		
			int[] rows = table.getSelectedRows();
			SwingUtilities.invokeLater(new CtrlVHolder(rows, s, isKey));
		}
		catch(Exception e)
		{Outside.err(this,"ctrlVFromJTable()",e);}
	}
	
	
	
	private void ctrlB()
	{
		try
		{
			Map sMap = getSelectedMap();
			performCtrlB.p(new Object[]{map, sMap});
		}
		catch(Exception e)
		{Outside.err(this,"ctrlB()",e);}
	}
	
	
    
    
	private class DeleteHolder implements Runnable
	{
		private String[] keys_;
		public DeleteHolder(String[] keys_) {this.keys_= keys_;}
		
		public void run()
		{
			table.getCellEditor().cancelCellEditing();
			for(int i=0;i<keys_.length;i++)
			map.remove(keys_[i]);
			model.run();
		}
	}
	
	
	private class CtrlVHolder implements Runnable
	{
		private int[] rows;
		private String s;
		private boolean isKey;
		
		public CtrlVHolder(int[] rows, String s, boolean isKey)
		{
			this.rows = rows;
			this.s = s;
			this.isKey = isKey;
		}
		
		public void run()
		{
			for(int i=0;i<rows.length;i++)
			{
				int row = rows[i];
				String key = (String) table.getValueAt(row,0);
				String value = (String) table.getValueAt(row,1);
				
				if(isKey)
				{
					map.remove(key);
					map.put(s,value);
				}
				else
				{
					map.put(key,s);
				}
			}
			model.run();
		}
	}
	
	
	private void editionTriggered()
	{send(this,"editionTriggered()");}
}