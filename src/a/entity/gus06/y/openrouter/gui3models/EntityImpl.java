package a.entity.gus06.y.openrouter.gui3models;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import java.awt.Font;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JCheckBox;
import java.util.ArrayList;


public class EntityImpl implements Entity, I, V, R, G, ActionListener {

	public String creationDate() {return "20251127";}
	
	public static final Color SELECTION_COLOR = new Color(210,235,235);
	
	public static final String KEY_APIKEY = "apikey";
	

	private Service fieldHolder;
	private Service tableTooltip;
	private Service linkerListField;
	private Service clearCopyPasteCut;
	private Service sortTable;
	private Service filterList;
	private Service clipboard;
	private Service listToString;
	private Service autoScroll;

	private JPanel panel;
	private JComponent field;
	private JTable table;
	private JScrollPane scroll;
	private JLabel labelNumber;
	private JCheckBox checkFree;
	private Icon iconLLM;
	
	private TableModel0 tableModel;
	
	private Thread t;
	private R engine;
	private List data;
	private List data0;
	

	public EntityImpl() throws Exception
	{
		fieldHolder = Outside.service(this, "*gus.data.editor.string.textfield.editor1");
		tableTooltip = Outside.service(this, "gus.x.swing.table.cust.tooltip1");
		linkerListField = Outside.service(this, "gus.x.swing.table.textfield.linker");
		clearCopyPasteCut = Outside.service(this, "gus.x.swing.comp.action.clear.copypastecut");
		sortTable = Outside.service(this,"gus06.swing.table.cust.sort2");
		filterList = Outside.service(this,"gus06.y.openrouter.gui3models.filterlist");
		clipboard = Outside.service(this,"gus06.clipboard.access.string");
		listToString = Outside.service(this,"gus06.tostring.list.join.n");
		autoScroll = Outside.service(this,"gus06.swing.scroll.autoposition1");
		iconLLM = (Icon) Outside.resource(this,"icon#AI_llm");

		field = (JComponent) fieldHolder.i();
		
		tableModel = new TableModel0();
		
		table = new JTable(tableModel);
		table.setShowGrid(false);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (e.isControlDown())
				{
					if (code == KeyEvent.VK_C) ctrl_c_copySelection();
				}
				else
				{
					if (code == KeyEvent.VK_F1) f1_selectModel();
				}
			}
		});
		
		TableCellRenderer0 renderer = new TableCellRenderer0();
		table.setDefaultRenderer(String.class, renderer);
		table.setDefaultRenderer(Long.class, renderer);
		
		clearCopyPasteCut.p(table);
		tableTooltip.p(table);
		sortTable.p(table);
		
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		autoScroll.p(scroll);
		
		labelNumber = new JLabel(" ");
		checkFree = new JCheckBox("free");
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(labelNumber,BorderLayout.CENTER);	
		bottomPanel.add(checkFree,BorderLayout.EAST);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(scroll,BorderLayout.CENTER);	
		panel.add(bottomPanel,BorderLayout.SOUTH);
		
		initColumnSize(0, 100);
		initColumnSize(1, 200);
		initColumnSize(2, 100);
		initColumnSize(3, 100);
		initColumnSize(4, 100);
		
		linkerListField.p(new Object[] { table, field });
		fieldHolder.addActionListener(e -> handleInputEdition());
		checkFree.addActionListener(e -> handleInputEdition());
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	public Object g() throws Exception
	{return selectedId();}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("data")) return data;
		if(key.equals("keys")) return new String[]{"data"};
		throw new Exception("Unknown key: "+key);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("engine"))
		{
			if(engine!=null) ((S)engine).removeActionListener(this);
			engine = (R) obj;
			((S)engine).addActionListener(this);
			rebuild();
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("modelsLoaded()")) {rebuild();return;}
		if(s.equals("updated()")) {refresh();return;}
	}
	
	
	private void rebuild()
	{
		try
		{
			data = (List) engine.r("modelList");
			if(data!=null) Collections.sort(data, this::compare);
			refresh();
		}
		catch(Exception e)
		{Outside.err(this,"rebuild()",e);}
	}
	
	private void refresh()
	{
		try
		{
			String search = (String) fieldHolder.g();
			boolean free = checkFree.isSelected();
			data0 = (List) filterList.t(new Object[]{data, search, free});
			labelNumber.setText(" "+buildNumber());
			tableModel.fireTableDataChanged();
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	private class TableModel0 extends AbstractTableModel
	{
		public int getColumnCount()
		{return 6;}
		
		public int getRowCount()
		{return data0==null?0:data0.size();}
		
		public String getColumnName(int y)
		{
			if(y==0) return "provider";
			if(y==1) return "name";
			if(y==2) return "created";
			if(y==3) return "token nb";
			if(y==4) return "pricing";
			if(y==5) return "description";
			return null;
		}
		
		public Class getColumnClass(int y)
		{
			if(y==0) return String.class;
			if(y==1) return String.class;
			if(y==2) return String.class;
			if(y==3) return Long.class;
			if(y==4) return String.class;
			if(y==5) return String.class;
			return null;
		}

		public Object getValueAt(int x, int y)
		{
			Map item = itemAt(x);
			if(item==null) return null;
			
			if(y==0) return provider(item);
			if(y==1) return name(item);
			if(y==2) return created(item);
			if(y==3) return tokenNb(item);
			if(y==4) return pricing(item);
			if(y==5) return description(item);
			return null;
		}
	}
	
	private class TableCellRenderer0 extends JLabel implements TableCellRenderer
	{
		private NumberFormat nfL = NumberFormat.getInstance(Locale.FRANCE);
		private Font fontP;
		private Font fontB;
		
		public TableCellRenderer0()
		{
			super();
			setOpaque(true);
			setBackground(Color.WHITE);
			setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
			
			fontP = getFont().deriveFont(Font.PLAIN);
			fontB = getFont().deriveFont(Font.BOLD);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, 
			boolean isSelected, boolean hasFocus, int row, int column)
		{
			setText(buildText(value, column));
			setIcon(buildIcon(value, column));
			setFont(buildFont(row));
			setBackground(buildBackground(isSelected));
			setHorizontalAlignment(align(value, column));
			return this;
		}
		
		private String buildText(Object value, int column)
		{
			if(value instanceof Long) return nfL.format((Long) value);
			return ""+value;
		}
		
		private int align(Object value, int column)
		{
			if(value instanceof Number) return JLabel.RIGHT;
			return JLabel.LEFT;
		}
		
		private Icon buildIcon(Object value, int column)
		{
			if(column==1) return iconLLM;
			return null;
		}
		
		private Color buildBackground(boolean isSelected)
		{
			return isSelected ? SELECTION_COLOR : Color.WHITE;
		}
		
		private Font buildFont(int row)
		{
			Map item = itemAt(row);
			String id = id(item);
			return Objects.equals(id,model()) ? fontB : fontP;
		}
	}

	private void handleInputEdition()
	{
		try{refresh();}
		catch (Exception e)
		{Outside.err(this, "handleInputEdition()", e);}
	}
	
	private void ctrl_c_copySelection()
	{
		try
		{
			List ids = selectedIds();
			if(!ids.isEmpty()) clipboard.p(listToString.t(ids));
		}
		catch(Exception e)
		{Outside.err(this,"ctrl_c_copySelection()",e);}
	}
	
	
	private void f1_selectModel()
	{
		try{((V)engine).v("select", selectedId());}
		catch(Exception e)
		{Outside.err(this,"f1_selectModel()",e);}
	}


	private void initColumnSize(int index, int size)
	{
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(size);
		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(size);
	}
	
	private int compare(Object o1, Object o2)
	{
		Map m1 = (Map) o1;
		Map m2 = (Map) o2;
		
		int r1 = created(m2).compareTo(created(m1));
		if(r1!=0) return r1;
		
		int r2 = provider(m1).compareTo(provider(m2));
		if(r2!=0) return r2;
		
		return name(m1).compareTo(name(m2));
	}
	
	private String model()
	{
		try{return engine!=null ? (String) engine.r("model") : null;}
		catch(Exception e)
		{Outside.err(this,"model()",e);}
		return "###";
	}
	
	private String buildNumber()
	{
		if(data0==null) return "";
		int size = data.size();
		int size0 = data0.size();
		if(size==size0) return ""+size;
		return size0+"/"+size;
	}
	
	private Map itemAt(int index)
	{
		if (data0 == null) return null;
		if (index < 0 || index >= data0.size()) return null;
		return (Map) data0.get(index);
	}
	
	private String id(Map item)
	{
		if(item==null) return null;
		return (String) item.get("id");
	}
	
	private String provider(Map item)
	{
		if(item==null) return null;
		String name = (String) item.get("name");
		if(name.contains(":")) return name.split(":")[0].trim();
		if(name.contains("/")) return name.split("/")[0].trim();
		
		String name_ = name.toLowerCase();
		if(name_.startsWith("qwen")) return "Qwen";
		if(name_.startsWith("cogito")) return "Deep Cogito";
		if(name_.startsWith("llama")) return "Meta";
		if(name_.startsWith("mistral")) return "Mistral";
		if(name_.startsWith("deepseek")) return "DeepSeek";
		return "";
	}
	
	private String name(Map item)
	{
		if(item==null) return null;
		String name = (String) item.get("name");
		if(name.contains(":")) return name.split(":")[1].trim();
		if(name.contains("/")) return name.split("/")[1].trim();
		return name;
	}
	
	private Long tokenNb(Map item)
	{
		if(item==null) return null;
		String v = (String) item.get("context_length");
		return Long.valueOf(v);
	}
	
	private String created(Map item)
	{
		if(item==null) return null;
		String v = (String) item.get("created");
		Date date = new Date(Long.parseLong(v)*1000);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	private String pricing(Map item)
	{
		if(item==null) return null;
		Map m = (Map) item.get("pricing");
		return (String) m.get("completion");
	}
	
	private String description(Map item)
	{
		if(item==null) return null;
		return (String) item.get("description");
	}
	
	// SELECTED
	
	private int selectedIndex()
	{
		int viewIndex = table.getSelectedRow();
		if (viewIndex < 0) return viewIndex;
		return table.convertRowIndexToModel(viewIndex);
	}
	
	private Map selectedItem()
	{return itemAt(selectedIndex());}
	
	private String selectedId()
	{return id(selectedItem());}
	
	
	// SELECTED (MULTI)

	private int[] selectedIndexes()
	{
		int[] viewIndexes = table.getSelectedRows();
		if (viewIndexes == null || viewIndexes.length == 0) return viewIndexes;
		int[] modelIndexes = new int[viewIndexes.length];
		for (int i = 0; i < viewIndexes.length; i++)
			modelIndexes[i] = table.convertRowIndexToModel(viewIndexes[i]);
		return modelIndexes;
	}
	
	private List selectedItems()
	{
		int[] idx = selectedIndexes();
		List list = new ArrayList();
		if (idx == null) return list;
		for (int i : idx) list.add(itemAt(i));
		return list;
	}
	
	private List selectedIds()
	{
		List items = selectedItems();
		List ids = new ArrayList();
		for (int i=0;i<items.size();i++)
		{
			Map item = (Map) items.get(i);
			ids.add(id(item));
		}
		return ids;
	}
}
