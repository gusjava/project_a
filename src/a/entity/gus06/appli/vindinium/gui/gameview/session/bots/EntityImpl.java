package a.entity.gus06.appli.vindinium.gui.gameview.session.bots;

import a.framework.*;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20170923";}

	
	public static final int PLAYER_NUMBER = 4;
	
	public static final int COL0 = 120;
	public static final int COL1 = 70;
	
	public static final Font FONT = new Font("Calibri",Font.PLAIN,18);
	public static final Font FONT_ME = new Font("Calibri",Font.BOLD,18);
	
	

	private Service heroColors;

	private TableModel1 model1;
	private JTable table1;

	private TableModel2 model2;
	private JTable table2;
	
	private JPanel panel;
	
	
	private String[] name;
	private int[] rank;
	private int[] gold;
	private int[] mines;
	private int[] life;
	private int[] elo;
	private int[] away;
	private Color[] colors;
	private int[] killed;
	private int[] punched;
	private int[] drinks;
	private int[] egold;
	private int[] pmines;
	private boolean[] crashed;
	private boolean[] immobile;
	
	private int me_id;



	public EntityImpl() throws Exception
	{
		heroColors = Outside.service(this,"gus06.appli.vindinium.data.hero.colorarray");
		colors = (Color[]) heroColors.g();
		
		model1 = new TableModel1();
		table1 = new JTable(model1);
		table1.setDefaultRenderer(Object.class,new TableCellRenderer0());
		table1.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		table1.setRowHeight(20);
		
		model2 = new TableModel2();
		table2 = new JTable(model2);
		table2.setDefaultRenderer(Object.class,new TableCellRenderer0());
		table2.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		table2.setRowHeight(20);
		
		initColumn(table1);
		initColumn(table2);
		
		panel = new JPanel(new GridLayout(2,1,5,5));
		panel.add(table1);
		panel.add(table2);
	}

	
	public Object i() throws Exception
	{return panel;}
	
	
	
	private void initColumn(JTable table)
	{
		initColumn(table,0,COL0);
		for(int i=1;i<table.getColumnCount();i++)
		initColumn(table,i,COL1);
	}
	
	
	private void initColumn(JTable table, int index, int size)
	{
		table.getColumnModel().getColumn(index).setMaxWidth(size);
		table.getColumnModel().getColumn(index).setMinWidth(size);
	}
	
	
	
	
	
	



	public void p(Object obj) throws Exception
	{
		Map data = (Map) obj;
		
		name = (String[]) data.get(DATA_H_._H_NAME);
		
		rank = (int[]) data.get(DATA_H_._H_RANK);
		gold = (int[]) data.get(DATA_H_._H_GOLD);
		mines = (int[]) data.get(DATA_H_._H_MINE);
		life = (int[]) data.get(DATA_H_._H_LIFE);
		elo = (int[]) data.get(DATA_H_._H_ELO);
		away = (int[]) data.get(DATA_H_._H_AWAY);
		
		drinks = (int[]) data.get(DATA_H_._H_PDRINK);
		punched = (int[]) data.get(DATA_H_._H_PHIT);
		killed = (int[]) data.get(DATA_H_._H_PKILLED);
		egold = (int[]) data.get(DATA_H_._H_FGOLD);
		pmines = (int[]) data.get(DATA_H_._H_PMINE);
		
		immobile = (boolean[]) data.get(DATA_H_._H_IMMOBILE);
		crashed = (boolean[]) data.get(DATA_H_._H_CRASHED);
		
		me_id = ((int[]) data.get(DATA_ME_._ME_STATE))[0];
		
		model1.fireTableDataChanged();
		model2.fireTableDataChanged();
	}

	
	
	
	
	

	
	
	
	private class TableModel1 extends AbstractTableModel
	{
		public int getColumnCount() {return 7;}
		public int getRowCount() {return PLAYER_NUMBER+1;}
		
		public String getColumnName(int y)
		{return "";}

		public Object getValueAt(int x, int y)
		{
			if(y==0) return name(x);
			if(y==1) return elo(x);
			if(y==2) return rank(x);
			if(y==3) return gold(x);
			if(y==4) return mines(x);
			if(y==5) return life(x);
			if(y==6) return away(x);
			return "";
		}
	}
	
	
	
	private class TableModel2 extends AbstractTableModel
	{
		public int getColumnCount() {return 7;}
		public int getRowCount() {return PLAYER_NUMBER+1;}
		
		public String getColumnName(int y)
		{return "";}

		public Object getValueAt(int x, int y)
		{
			if(y==0) return name(x);
			if(y==1) return drinks(x);
			if(y==2) return punched(x);
			if(y==3) return killed(x);
			if(y==4) return pmine(x);
			if(y==5) return egold(x);
			if(y==6) return immobile(x);
			return "";
		}
	}
	
	
	
	
	
	
	private String name(int v) {return value(name,"Bot name",v);}
	private String elo(int v) {return value(elo,"ELO",v);}
	private String rank(int v) {return value(rank,"Rank",v);}
	private String gold(int v) {return value(gold,"Gold",v);}
	private String mines(int v) {return value(mines,"Mines",v);}
	private String life(int v) {return value(life,"Life",v);}
	private String away(int v) {return value(away,"Away",v);}
	private String drinks(int v) {return value(drinks,"Drinks",v);}
	private String punched(int v) {return value(punched,"Punched",v);}
	private String killed(int v) {return value(killed,"Killed",v);}
	private String pmine(int v) {return value(pmines,"P-Mines",v);}
	private String egold(int v) {return value(egold,"E-gold",v);}
	private String immobile(int v) {return value(immobile,"Blocked",v);}
	
	
	
	
	
	
	
	private String value(int[] tab, String name, int v)
	{
		if(v==0) return name;
		return tab!=null?""+tab[v-1]:"";
	}
	
	private String value(String[] tab, String name, int v)
	{
		if(v==0) return name;
		return tab!=null?tab[v-1]:"";
	}
	
	private String value(boolean[] tab, String name, int v)
	{
		if(v==0) return name;
		return tab!=null?""+tab[v-1]:"";
	}
	


	
	
	
	
	
	private class TableCellRenderer0 extends JLabel implements TableCellRenderer
	{
		public TableCellRenderer0()
		{
			super();
			setOpaque(true);
			setFont(FONT);
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int x, int y)
		{
			setText(" "+value);
			setForeground(foreground(x));
			setBackground(background(x));
			setFont(font(x));
			return this;
		}
	}
	
	
	
	private Color foreground(int v)
	{
		if(v==0) return Color.WHITE;
		return colors!=null?colors[v-1]:Color.WHITE;
	}
	
	
	private Color background(int v)
	{
		if(v==0) return Color.BLACK;
		if(crashed!=null && crashed[v-1]) return Color.DARK_GRAY;
		return Color.BLACK;
	}
	
	
	private Font font(int v)
	{
		if(v==0) return FONT;
		return v==me_id?FONT_ME:FONT;
	}
	
}
