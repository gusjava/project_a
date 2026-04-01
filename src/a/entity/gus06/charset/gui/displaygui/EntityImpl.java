package a.entity.gus06.charset.gui.displaygui;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;



public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150323";}


	private SortedMap charSetMap;
	private ArrayList charSetNames;
	
	private Charset defaultCharset;
	
	private CharsetTableModel model;
	private JPanel panel;
	
	
	
	private Charset charset(int x)
	{return (Charset) charSetMap.get(charSetNames.get(x));}
	
	
	private boolean isAutoDetected(Charset c)
	{return c.newDecoder().isAutoDetecting();}
	
	

	public EntityImpl() throws Exception
	{
		charSetMap = Charset.availableCharsets();
		charSetNames = new ArrayList(charSetMap.keySet());
		defaultCharset = Charset.defaultCharset();
		
		model = new CharsetTableModel();
		JTable table = new JTable(model);
		table.setDefaultRenderer(String.class,new CharsetTableCellRenderer());
		
		JLabel label = new JLabel("charset number = "+charSetMap.size());
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
	}



	public Object i() throws Exception
	{return panel;}

	
	
	private class CharsetTableModel extends AbstractTableModel
	{
		public int getRowCount() {return charSetMap.size();}
		public int getColumnCount() {return 5;}
		
		public Class getColumnClass(int y)
		{return String.class;}
		
		public String getColumnName(int y)
		{
			if(y==0) return "Name";
			if(y==1) return "Alias";
			if(y==2) return "Registered";
			if(y==3) return "Can encode";
			if(y==4) return "Auto detecting";
			return null;
		}

		public Object getValueAt(int x, int y)
		{
			Charset c = charset(x);
			if(y==0) return c.displayName();
			if(y==1) return convert(c.aliases());
			if(y==2) return ""+c.isRegistered();
			if(y==3) return ""+c.canEncode();
			if(y==4) return ""+isAutoDetected(c);
			return null;
		}
	}
	
	
	
	
	private class CharsetTableCellRenderer extends JLabel implements TableCellRenderer
	{
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			Charset c = charset(row);
			if(c.equals(defaultCharset))
				setFont(getFont().deriveFont(Font.BOLD));
			else setFont(getFont().deriveFont(Font.PLAIN));
			
			String v = (String)value;
			if(v.equals("false")) setForeground(Color.RED);
			else setForeground(Color.BLACK);
			
			setText(v);
			return this;
		}
	}
	
	
	
	private String convert(Set set)
	{
		StringBuffer b = new StringBuffer();
		Iterator it = set.iterator();
		while(it.hasNext()) b.append(it.next()+"  ");
		return b.toString();
	}

}