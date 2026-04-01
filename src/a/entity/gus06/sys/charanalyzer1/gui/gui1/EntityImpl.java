package a.entity.gus06.sys.charanalyzer1.gui.gui1;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.table.AbstractTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, ActionListener, I, P {

	public String creationDate() {return "20180329";}


	private Service textChanged;

	public static final long LAPSE = 500;


	private JPanel panel;
	private JTable table;
	private JLabel label;
	private AnalyzerTableModel model;
	private String selection = "";
	
	private JTextComponent comp;
	
	private Timer timer;
	private TimerTask task;

	
	
	public EntityImpl() throws Exception
	{
		textChanged = Outside.service(this,"gus06.swing.textcomp.textchanged.delayed");
		
		model = new AnalyzerTableModel();
		table =  new JTable(model);
        
		label = new JLabel("0");
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
	
		setSize(new Dimension(0,150));
		
		task = new TimerTask() {public void run() {updateGui();}};
		timer = new Timer("TIMER_"+getClass().getName());
		timer.schedule(task,new Date(),LAPSE);
	}
		
		
	private void setSize(Dimension d)
	{
		panel.setMaximumSize(d);
		panel.setMinimumSize(d);
		panel.setPreferredSize(d);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	
	public void p(Object obj) throws Exception
	{
		if(comp!=null) throw new Exception("Comp already initialized");
		comp = (JTextComponent) obj;
		
		S sup = (S) textChanged.t(comp);
		sup.addActionListener(this);
	}
	
	
	

	public void actionPerformed(ActionEvent e)
	{updateGui();}
		
	
	private void updateGui()
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){updateGui_();}
		});
	}
	
	private void updateGui_()
	{
		boolean updated = updateSelection();
		if(!updated) return;
		
		label.setText(""+selection.length());
		model.fireTableStructureChanged();
	}
    
	private boolean updateSelection()
	{
		String newSelection = comp==null?"":comp.getSelectedText();
		if(newSelection==null) newSelection = "";
		
		if(newSelection.equals(selection)) return false;
		selection = newSelection;
		return true;
	}
	
	
	
	private class AnalyzerTableModel extends AbstractTableModel
	{
		public int getRowCount() { return selection.length();}
		public int getColumnCount() {return 4;}

		public Class getColumnClass(int y)
		{return String.class;}
        
		public String getColumnName(int y)
		{
			if(y==0) return "char";
			if(y==1) return "code point";
			if(y==2) return "\\u{hexa}";
			if(y==3) return "unicode block";
			return null;
		}
        
        
		public Object getValueAt(int x, int y)
		{
			char c = selection.charAt(x);
			if(y==0) return toString(c);
			if(y==1) return ""+selection.codePointAt(x);
			if(y==2) return "\\u"+hexa(selection.codePointAt(x));
			if(y==3) return Character.UnicodeBlock.of(c).toString();
			return null;
		}
		
		private String toString(char c)
		{
			if(c=='\n') return "\\n";
			if(c=='\t') return "\\t";
			if(c=='\r') return "\\r";
			if(c==' ') return "space";
			return ""+c;
		}
	}
	
	
	
		
	private String hexa(int n)
	{
		String v = ""+Integer.toHexString(n);
		while(v.length()<4) v="0"+v;
		return v;
	}
}
