package a.entity.gus06.dir.explorer.resource.ling.dicopanel;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.*;
import java.util.*;
import java.awt.Insets;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.GridLayout;


public class EntityImpl implements Entity, I, P, ActionListener, ListSelectionListener, DocumentListener {

	public String creationDate() {return "20140811";}
	
	public static final Font FONT = new Font("Calibri",Font.PLAIN,18);
	

	private Service factory;
	private Service splitCust;
	private Service buildPanel;
	private Service quickReplace;
	private Service fieldHolder;
	private Service listFilter;
	private Service buildButton;
	private Service input;
	private Service confirm;
	private Service custComp;
	
	private JTable table;
	private TableModel1 model;

	private Map mapFiles;
	private Map mapProps;
	private List keys;
	private List filteredKeys;
	private List langs;
	
	private JTextArea area;
	private JSplitPane split;
	private JComponent field;
	
	private JButton buttonAdd;
	private JButton buttonDuplicate;
	private JButton buttonRemove;
	
	
	

	public EntityImpl() throws Exception
	{
		factory = Outside.service(this,"gus06.swing.textarea.factory1");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		buildPanel = Outside.service(this,"gus06.swing.textarea.buildpanel1");
		quickReplace = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_w.quickreplace");
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		listFilter = Outside.service(this,"gus06.list.filter.rule.one");
		buildButton = Outside.service(this,"gus06.swing.button.build.execute");
		input = Outside.service(this,"gus06.input.text.dialog");
		confirm = Outside.service(this,"gus06.input.confirm.dialog");
		custComp = Outside.service(this,"gus06.data.editor.string.textarea.editor1.custcomp");
		
		model = new TableModel1();
		table = new JTable(model);
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		table.getSelectionModel().addListSelectionListener(this);
		table.getColumnModel().getSelectionModel().addListSelectionListener(this);
		
		area = (JTextArea)  factory.i();
		area.setEnabled(false);
		area.setLineWrap(true);
		area.setFont(FONT);
		area.setMargin(new Insets(3,3,3,3));
		area.getDocument().addDocumentListener(this);
		custComp.p(area);
		
		quickReplace.p(area);
		
		field = (JComponent) fieldHolder.i();
		fieldHolder.addActionListener(this);
		
		buttonAdd = (JButton) buildButton.t((E) this::add);
		buttonDuplicate = (JButton) buildButton.t((E) this::duplicate);
		buttonRemove = (JButton) buildButton.t((E) this::remove);
		
		buttonAdd.setText("Add");
		buttonDuplicate.setText("Duplicate");
		buttonRemove.setText("Remove");
		
		buttonAdd.setEnabled(false);
		buttonDuplicate.setEnabled(false);
		buttonRemove.setEnabled(false);
		
		JPanel panelTop = new JPanel(new BorderLayout());
		panelTop.add(field,BorderLayout.NORTH);
		panelTop.add(new JScrollPane(table),BorderLayout.CENTER);
		
		JPanel panelButtons = new JPanel(new GridLayout(1,3));
		panelButtons.add(buttonAdd);
		panelButtons.add(buttonDuplicate);
		panelButtons.add(buttonRemove);
		
		JPanel panelArea = (JPanel) buildPanel.t(area);
		
		JPanel panelBottom = new JPanel(new BorderLayout());
		panelBottom.add(panelArea,BorderLayout.CENTER);
		panelBottom.add(panelButtons,BorderLayout.SOUTH);
		
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitCust.p(split);
		
		split.setLeftComponent(panelTop);
		split.setRightComponent(panelBottom);
		split.setDividerLocation(200);
	}
	
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		mapFiles = (Map) obj;
		if(mapFiles==null) resetGui();
		else updateGui();
	}
	
	
	
	private void resetGui()
	{
		buttonAdd.setEnabled(false);
		buttonDuplicate.setEnabled(false);
		buttonRemove.setEnabled(false);
		
		mapProps = null;
		keys = null;
		langs = null;
		model.fireTableStructureChanged();
	}
	
	
	private void updateGui()
	{
		try
		{
			mapProps = new HashMap();
			Set keys_ = new HashSet();
		
			Iterator it = mapFiles.keySet().iterator();
			while(it.hasNext())
			{
				String lang = (String) it.next();
				File file = (File) mapFiles.get(lang);
				Properties prop = load(file);
				
				keys_.addAll(prop.keySet());
				mapProps.put(lang,prop);
			}
			
			keys = new ArrayList(keys_);
			Collections.sort(keys);
			
			filteredKeys = new ArrayList(keys);
			
			langs = new ArrayList(mapFiles.keySet());
			Collections.sort(langs);
			
			buttonAdd.setEnabled(true);
			buttonDuplicate.setEnabled(false);
			buttonRemove.setEnabled(false);
			
			model.fireTableStructureChanged();
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	
	private Properties load(File file) throws Exception
	{
		Properties prop = new Properties();
		if(!file.exists()) return prop;
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
		fis.close();
		return prop;
	}
	
	
	private void save(File file, Properties prop) throws Exception
	{
		FileOutputStream fos = new FileOutputStream(file);
		prop.store(fos,"");
		fos.close();
	}
	
	
	
	
	private File file(String lang)
	{return (File) mapFiles.get(lang);}
		
	private Properties prop(String lang)
	{return (Properties) mapProps.get(lang);}
	
	private String lang(int index)
	{return (String) langs.get(index);}
	
	private String key(int index)
	{return (String) filteredKeys.get(index);}
	
	
	
	
	
	private void editKey(String key, String key1)
	{
		try
		{
			for(int i=0;i<langs.size();i++)
			{
				String lang = lang(i);
				File f = file(lang);
				Properties p = prop(lang);
	
				String value = p.containsKey(key)?p.getProperty(key):"";
				p.remove(key);
				if(!key1.equals("")) p.setProperty(key1,value);
				save(f,p);
			}
			
			int index = keys.indexOf(key);
			boolean removed = keys.remove(key);
			if(removed && !key1.equals(""))keys.add(index,key1);
		}
		catch(Exception e)
		{Outside.err(this,"editKey(String,String)",e);}
	}
	
	
	
	private void editLing(String key, String lang, String value)
	{
		try
		{
			Properties p = prop(lang);
			File f = file(lang);
			p.setProperty(key,value);
			save(f,p);
		}
		catch(Exception e)
		{Outside.err(this,"editLing(String,String,String)",e);}
	}
	
	
	
	
	
	private class TableModel1 extends AbstractTableModel
	{
		public int getColumnCount() {return langs==null?1:langs.size()+1;}
		public int getRowCount() {return filteredKeys==null?0:filteredKeys.size();}
		public boolean isCellEditable(int x, int y) {return false;}
		public Class getColumnClass(int y){return String.class;}
		
		public String getColumnName(int y)
		{
			if(y==0) return "KEYS";
			return lang(y-1);
		}
		
		public Object getValueAt(int x, int y)
		{
			if(keys.isEmpty()) return "";
			String key = key(x);
			if(y==0) return key;
			
			String lang = lang(y-1);
			Properties p = prop(lang);
		
			if(!p.containsKey(key)) return "";
			return p.getProperty(key);
		}
		
		public void setValueAt(Object value, int x, int y)
		{
			String v = (String) value;
			String key = key(x);
			if(y==0) editKey(key,v);
			else editLing(key,lang(y-1),v);
		}
	}
	
	
	
	private String getSelectedCell()
	{
		if(table.getSelectionModel().isSelectionEmpty()) return null;
		
		int x = table.getSelectedRow();
		int y = table.getSelectedColumn();
		return (String) table.getValueAt(x,y);
	}
	
	
	private String getSelectedKey()
	{
		if(table.getSelectionModel().isSelectionEmpty()) return null;
		
		int x = table.getSelectedRow();
		return (String) table.getValueAt(x,0);
	}
	
	
	
	
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	
	public void changedUpdate(DocumentEvent e) {}
        public void insertUpdate(DocumentEvent e) {textChanged();}
        public void removeUpdate(DocumentEvent e) {textChanged();}
	
	
	
	private void selectionChanged()
	{
		try
		{
			String cell = getSelectedCell();
			if(cell==null)
			{
				setAreaText("");
				area.setEnabled(false);
				buttonDuplicate.setEnabled(false);
				buttonRemove.setEnabled(false);
				return;
			}
			
			setAreaText(cell);
			area.setEnabled(true);
			buttonDuplicate.setEnabled(true);
			buttonRemove.setEnabled(true);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	
	private void setAreaText(String text)
	{
		area.getDocument().removeDocumentListener(this);
		area.setText(text);
		area.getDocument().addDocumentListener(this);
	}
	
	
	
	private void textChanged()
	{
		if(table.getSelectionModel().isSelectionEmpty()) return;
		
		String value = area.getText();
		int x = table.getSelectedRow();
		int y = table.getSelectedColumn();
		
		table.setValueAt(value,x,y);
		table.repaint();
	}


	public void actionPerformed(ActionEvent e)
	{refresh();}
	
	
	
	private void refresh()
	{
		try
		{
			String input = (String) fieldHolder.g();
			filteredKeys = (List) listFilter.t(new Object[]{keys,input});
			model.fireTableStructureChanged();
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}



	private void add()
	{
		try
		{
			String key = (String) input.t("Enter new key:");
			if(key==null || key.equals("")) return;
		
			for(int i=0;i<langs.size();i++)
			{
				String lang = lang(i);
				File f = file(lang);
				Properties p = prop(lang);
				
				if(!p.containsKey(key))
				{
					p.setProperty(key,"");
					save(f,p);
				}
			}
			
			if(!keys.contains(key))
			{
				keys.add(key);
				Collections.sort(keys);
			}
			refresh();
			
		}
		catch(Exception e)
		{Outside.err(this,"add()",e);}
	}
	
	
	private void duplicate()
	{
		try
		{
			String key = getSelectedKey();
			if(key==null) return;
			
			String key1 = (String) input.t(new String[]{"Enter new key:",key});
			if(key1==null || key1.equals("")) return;
			
			for(int i=0;i<langs.size();i++)
			{
				String lang = lang(i);
				File f = file(lang);
				Properties p = prop(lang);
				
				if(!p.containsKey(key1))
				{
					String value = p.containsKey(key) ? p.getProperty(key) : "";
					p.setProperty(key1,value);
					save(f,p);
				}
			}
			
			if(!keys.contains(key1))
			{
				keys.add(key1);
				Collections.sort(keys);
			}
			refresh();
		}
		catch(Exception e)
		{Outside.err(this,"duplicate()",e);}
	}
	
	
	private void remove()
	{
		try
		{
			String key = getSelectedKey();
			if(key==null) return;
			
			boolean ok = confirm.f("Are you sure to delete key "+key+" ?");
			if(!ok) return;
			
			for(int i=0;i<langs.size();i++)
			{
				String lang = lang(i);
				File f = file(lang);
				Properties p = prop(lang);
				
				if(p.containsKey(key))
				{
					p.remove(key);
					save(f,p);
				}
			}
			
			if(keys.contains(key))
			{
				keys.remove(key);
				Collections.sort(keys);
			}
			refresh();
		}
		catch(Exception e)
		{Outside.err(this,"remove()",e);}
	}
}