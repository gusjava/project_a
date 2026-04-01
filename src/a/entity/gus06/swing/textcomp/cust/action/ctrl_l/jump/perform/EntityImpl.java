package a.entity.gus06.swing.textcomp.cust.action.ctrl_l.jump.perform;

import a.framework.*;
import javax.swing.text.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import java.util.List;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import java.awt.Font;
import javax.swing.table.TableCellRenderer;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200316";}

	public static final String KEY_HANDLER = "ctrl_l_handler";


	private Service applyToComp;
	private Service jump;
	private Service dialog;
	private Service normalize;
	private Service onKey;
	private Service onClicked;
	
	private JTable table;
	private TableModel0 model;
	private JPanel panel;
	private List values;
	
	
	public EntityImpl() throws Exception
	{
		applyToComp = Outside.service(this,"gus06.appli.gusexplorer.gui.editor.fillbar.applytocomp.p");
		jump = Outside.service(this,"gus06.swing.textcomp.caret.jump.byrule");
		dialog = Outside.service(this,"gus06.swing.dialog.blocked1.okcancel0");
		normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
		onClicked = Outside.service(this,"gus06.swing.comp.cust3.on.mouseclicked.execute");
		
		model = new TableModel0();
		
		table = new JTable(model);
		table.setGridColor(Color.LIGHT_GRAY);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultRenderer(Object.class,new TableCellRenderer0());
		
		initColumnSize(0,50);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll,BorderLayout.CENTER);
		
		onKey.p(new Object[]{table,"escape",(E) this::cancel});
		onClicked.p(new Object[]{table,(E) this::ok});
		
		dialog.v("width",1000);
		dialog.v("height",500);
	}
	
	
	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	
	private void perform(JTextComponent comp) throws Exception
	{
		boolean applied = applyToComp.f(new Object[]{comp,comp.getSelectedText(),KEY_HANDLER});
		if(applied) return;
		
		String rule = buildRule(comp);
		if(rule==null) return;
		
		boolean done = jump.f(new Object[]{comp,rule});
		if(!done)
		{
			String msg = "Invalid jump rule: "+rule;
			JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	
	
	private String buildRule(JTextComponent comp) throws Exception
	{
		String selection = selection(comp);
		if(selection!=null) 
		{
			if(isValidPositionRule(selection)) return selection;
			return buildByWord(comp, selection);
		}
		
		String input = JOptionPane.showInputDialog(null,"Please, enter jump rule:");
		if(input==null) return null;
		
		if(input.startsWith(">")) return buildByWord(comp, input.substring(1));
		return input;
	}
	
	
	
	private boolean isValidPositionRule(String rule)
	{
		if(rule.matches("[0-9]+(/[0-9]+)?")) return true;
		if(rule.matches(":[0-9]+(/[0-9]+)?")) return true;
		if(rule.matches("[0-9]+:[0-9]+(/[0-9]+)?")) return true;
		return false;
	}
	
	
	
	private String buildByWord(JTextComponent comp, String word) throws Exception
	{
		String text = comp.getText();
		String[] lines = text.split("\n");
		String query = (String) normalize.t(word);
		
		values = new ArrayList();
		for(int i=0;i<lines.length;i++)
		{
			String line = lines[i];
			String line_ = (String) normalize.t(line);
			int lineNb = i+1;
			
			if(line_.contains(query)) values.add(new Object[]{lineNb,line});
		}
		
		if(values.size()==0) return null;
		if(values.size()==1) return "" + valueAt(0)[0];
		
		model.fireTableDataChanged();
		
		boolean result = dialog.f(panel);
		if(!result) return null;
		
		int row = table.getSelectedRow();
		return (String) table.getValueAt(row,0);
	}
	
	
	private Object[] valueAt(int index)
	{return (Object[]) values.get(index);}
	
	
	
	private String selection(JTextComponent comp)
	{
		String s = comp.getSelectedText();
		if(s!=null && !s.equals("")) return s;
		return null;
	}
	
	
	
	private void cancel()
	{
		try{dialog.v("do","cancel");}
		catch(Exception e)
		{Outside.err(this,"cancel()",e);}
	}
	
	private void ok()
	{
		try{dialog.v("do","ok");}
		catch(Exception e)
		{Outside.err(this,"ok()",e);}
	}
	
	
	
	private void initColumnSize(int index, int size)
	{
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(size);
		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(size);
	}
	
	
	
	
	private class TableModel0 extends AbstractTableModel
	{
		public int getColumnCount() {return 2;}
		public int getRowCount() {return values.size();}
		public Class getColumnClass(int y) {return Object.class;}
		
		public String getColumnName(int y)
		{
			if(y==0) return "Nb";
			if(y==1) return "Line";
			return null;
		}

		public Object getValueAt(int x, int y)
		{
			if(values.size()<=x) return null;
			
			Object[] value = valueAt(x);
			if(y==0) return ""+value[0];
			if(y==1) return ((String) value[1]).trim();
			
			return null;
		}
	}
	
	
	private class TableCellRenderer0 extends JLabel implements TableCellRenderer
	{
		private Font font_p;
		private Font font_b;
		private Color gray;
		
		public TableCellRenderer0()
		{
			super();
			setOpaque(true);
			
			font_p = getFont().deriveFont(Font.PLAIN);
			font_b = getFont().deriveFont(Font.BOLD);
			gray = getBackground();
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			setBackground(column==0 ? gray : Color.WHITE);
			setText(" "+value);
			return this;
		}
	}
}