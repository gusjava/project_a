package a.entity.gus06.sys.datepicker1.fr.gui.panel;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EntityImpl extends S1 implements Entity, P, G, I, V, R, ListSelectionListener, ItemListener {

	public String creationDate() {return "20160616";}
	
	public static final Dimension DIM = new Dimension(154,142);


	private Service monthTableHolder;
	private Service yearComboHolder;
	private Service monthComboHolder;
	
	private JPanel panel;
	private JTable table;
	private JComboBox yearComboBox;
	private JComboBox monthComboBox;
	
	private int[] data;
	private int[] ym;


	public EntityImpl() throws Exception
	{
		monthTableHolder = Outside.service(this,"*gus06.sys.datepicker1.fr.gui.monthtable");
		yearComboHolder = Outside.service(this,"*gus06.sys.datepicker1.fr.gui.combo.years");
		monthComboHolder = Outside.service(this,"*gus06.sys.datepicker1.fr.gui.combo.months");
		
		yearComboBox = (JComboBox) yearComboHolder.i();
		monthComboBox = (JComboBox) monthComboHolder.i();
		table = (JTable) monthTableHolder.i();
		
		JPanel p_top = new JPanel(new BorderLayout());
		
		p_top.add(monthComboBox,BorderLayout.CENTER);
		p_top.add(yearComboBox,BorderLayout.EAST);
		
		panel = new JPanel(new BorderLayout());
		
		panel.setPreferredSize(DIM);
		panel.setMinimumSize(DIM);
		panel.setMaximumSize(DIM);
		
		panel.add(p_top,BorderLayout.NORTH);
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
		
		table.getSelectionModel().addListSelectionListener(this);
		table.getColumnModel().getSelectionModel().addListSelectionListener(this);
		
		yearComboBox.addItemListener(this);
		monthComboBox.addItemListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	
	public void p(Object obj) throws Exception
	{
		data = (int[]) obj;
		ym = new int[]{data[0],data[1]};
		updateAll();
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("ym")) {changeYM((int[]) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("ym")) return ym;
		if(key.equals("keys")) return new String[]{"ym"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void changeYM(int[] ym)
	{
		this.ym = new int[]{ym[0],ym[1]};
		updateAll();
	}
	
	
	
	
	
	public void valueChanged(ListSelectionEvent e)
	{daySelectionChanged();}
	
	public void itemStateChanged(ItemEvent e)
	{monthYearSelectionChanged();}
	
	
	
	
	private void updateAll()
	{
		updateMonthTable();
		updateMonthCombo();
		updateYearCombo();
	}
	
	
	private void updateMonthTable()
	{
		try
		{
			table.getSelectionModel().removeListSelectionListener(this);
			monthTableHolder.p(ym);
			checkTableSelection();
			table.getSelectionModel().addListSelectionListener(this);
		}
		catch(Exception e)
		{Outside.err(this,"updateTable()",e);}
	}
	
	
	private void updateMonthCombo()
	{
		monthComboBox.removeItemListener(this);
		monthComboBox.setSelectedIndex(ym[1]-1);
		monthComboBox.addItemListener(this);
	}
	
	private void updateYearCombo()
	{
		yearComboBox.removeItemListener(this);
		yearComboBox.setSelectedItem(Integer.valueOf(ym[0]));
		yearComboBox.addItemListener(this);
	}
	
	
	
	
	private void daySelectionChanged()
	{
		int x = table.getSelectedRow();
		int y = table.getSelectedColumn();
		if(x==-1 ||y==-1) return;
		
		int[] selected = (int[]) table.getValueAt(x,y);
		if(selected==null) {updateMonthTable();return;}
		
		data = selected;
		dataModified();
	}
	
	private void monthYearSelectionChanged()
	{
		int year = ((Integer) yearComboBox.getSelectedItem()).intValue();
		int month = monthComboBox.getSelectedIndex()+1;
		
		ym = new int[]{year,month};
		updateMonthTable();
		ymModified();
	}
	
	
	
	private void checkTableSelection()
	{
		for(int i=0;i<table.getRowCount();i++)
		for(int j=0;j<table.getColumnCount();j++)
		{
			int[] a = (int[]) table.getValueAt(i,j);
			if(equals(a,data)) {table.changeSelection(i,j,false,false);return;}
		}
	}
	
	
	private boolean equals(int[] a, int[] b)
	{
		if(a==null || b==null) return false;
		return a[0]==b[0] && a[1]==b[1] && a[2]==b[2];
	}
	
	
	private void ymModified()
	{send(this,"ymModified()");}
	
	private void dataModified()
	{send(this,"dataModified()");}
}
