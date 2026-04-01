package a.entity.gus06.java.tool.locale.gui1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Locale;
import java.util.MissingResourceException;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20191112";}


	private Service sorter;
	
	private Locale[] locales;
	private String[] ISOCountries;
	private String[] ISOLanguages;
	
	private Locale defaultLocale;
	private Locale selected;
	
	private JTable table;
	private TableModel1 model;
	private JTabbedPane tabbed;



	public EntityImpl() throws Exception
	{
		sorter = Outside.service(this,"gus06.swing.table.cust.sort1");
		
		locales = Locale.getAvailableLocales();
		ISOCountries = Locale.getISOCountries();
		ISOLanguages = Locale.getISOLanguages();
		defaultLocale = Locale.getDefault();
		
		model = new TableModel1();
		table = new JTable(model);
		table.setDefaultRenderer(Locale.class,new TableCellRenderer1());
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {selectionChanged();}
		});
		
		sorter.p(table);

		JLabel label = new JLabel("locale number = "+locales.length);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
		
		tabbed = new JTabbedPane();
		tabbed.addTab("locales",panel);
		tabbed.addTab("ISO Countries",buildPanel(ISOCountries));
		tabbed.addTab("ISO Languages",buildPanel(ISOLanguages));
	}

	public Object i() throws Exception
	{return tabbed;}
	
	
	private void selectionChanged()
	{
		if(table.getSelectionModel().isSelectionEmpty())
		{selected = null;return;}
		
		int selectedRow = table.getSelectedRow();
		selected = (Locale) table.getValueAt(selectedRow, 0);
		table.repaint();
	}
	
	
	private JPanel buildPanel(String[] array)
	{
		JList list = new JList(array);
		JLabel label = new JLabel(" "+array.length);
		
		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(list),BorderLayout.CENTER);
		p.add(label,BorderLayout.SOUTH);
		return p;
	}

	
	
	private class TableModel1 extends AbstractTableModel
	{
		public int getRowCount() {return locales.length;}
		public int getColumnCount() {return 7;}
		
		public Class getColumnClass(int y)
		{return Locale.class;}
		
		public String getColumnName(int y)
		{
			if(y==0) return "Country Code";
			if(y==1) return "Language Code";
			if(y==2) return "Country ISO3 Code";
			if(y==3) return "Language ISO3 Code";
			if(y==4) return "Country Name";
			if(y==5) return "Language Name";
			if(y==6) return "Locale Name";
			return null;
		}

		public Object getValueAt(int x, int y)
		{return locales[x];}
	}
	
	
	
	
	private String getISO3Country(Locale locale)
	{
		try{return locale.getISO3Country();}
		catch(MissingResourceException e)
		{return e.getMessage();}
	}
	
	private String getISO3Language(Locale locale)
	{
		try{return locale.getISO3Language();}
		catch(MissingResourceException e)
		{return e.getMessage();}
	}
	
	
	
	private String displayCountry(Locale locale)
	{
		if(selected==null) return locale.getDisplayCountry();
		return locale.getDisplayCountry(selected);
	}
	
	
	
	private String displayLanguage(Locale locale)
	{
		if(selected==null) return locale.getDisplayLanguage();
		return locale.getDisplayLanguage(selected);
	}
	
	
	
	private String displayName(Locale locale)
	{
		if(selected==null) return locale.getDisplayName();
		return locale.getDisplayName(selected);
	}
	
	
	
	
	private class TableCellRenderer1 extends JLabel implements TableCellRenderer
	{
		public TableCellRenderer1()
		{
			super();
			setOpaque(true);
			setBackground(Color.WHITE);
			setFont(getFont().deriveFont(Font.PLAIN));
		}
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int x, int y)
		{
			Locale locale = (Locale)value;
			String countryCode = locale.getCountry();
			
			if(locale.equals(defaultLocale))
				setForeground(Color.RED);
			else setForeground(Color.BLACK);
			
			if(isSelected)
				setBackground(Color.LIGHT_GRAY);
			else setBackground(Color.WHITE);
			
			if(y==0) setText(countryCode);
			if(y==1) setText(locale.getLanguage());
			if(y==2) setText(getISO3Country(locale));
			if(y==3) setText(getISO3Language(locale));
			if(y==4) setText(displayCountry(locale));
			if(y==5) setText(displayLanguage(locale));
			if(y==6) setText(displayName(locale));
			
			return this;
		}
	}
}
