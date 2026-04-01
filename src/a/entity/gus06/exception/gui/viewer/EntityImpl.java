package a.entity.gus06.exception.gui.viewer;

import a.framework.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class EntityImpl implements Entity, I, P, ActionListener, ListSelectionListener {

	public String creationDate() {return "20140730";}

	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	private String timeStamp(Date d){return sdf.format(d);}
	
	private Service exceptionToString;
	private Service custArea;
	private Service splitCust;
	private Service copyAll;
	private Service buildBar;
	private Service autoScroll;
	
	private List errors;
    
	private JSplitPane split;
	private JTable table;
	private JTextArea area;
	private JScrollPane scroll;
	private JLabel numberLabel;
    
	private TableModel0 model;
	private Action copyAction;
	private int previousSelectedRow = -1;
	
	public EntityImpl() throws Exception
	{
		exceptionToString = Outside.service(this,"gus06.tostring.exception.ste");
		custArea = Outside.service(this,"gus06.swing.textcomp.cust.console1.black.red");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		copyAll = Outside.service(this,"gus06.swing.textcomp.build.action.copyall");
		buildBar = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		autoScroll = Outside.service(this,"gus06.swing.scroll.autoposition1");
		
		errors = (List) Outside.resource(this,"errlist");
        
		model = new TableModel0();
		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		table.getSelectionModel().addListSelectionListener(this);
        
		area = new JTextArea();
		area.setEditable(false);
		custArea.p(area);
		
		scroll = new JScrollPane(area);
		autoScroll.p(scroll);
		
		copyAction = (Action) copyAll.t(area);
		
		numberLabel = new JLabel(" 0");
        
		JToolBar bar = (JToolBar) buildBar.t(copyAction);
		JLabel titleLabel = titleLabel("Exception detail view");
        
		JPanel p0 = new JPanel(new BorderLayout());
		p0.add(bar,BorderLayout.EAST);
		p0.add(numberLabel,BorderLayout.CENTER);
		
		JPanel p = new JPanel(new BorderLayout());
		p.add(titleLabel,BorderLayout.NORTH);
		p.add(scroll,BorderLayout.CENTER);
		p.add(p0,BorderLayout.SOUTH);
        
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitCust.p(split);
		
		split.setOneTouchExpandable(true);
		split.setDividerLocation(200);
        
		split.setLeftComponent(new JScrollPane(table));
		split.setRightComponent(p);
		
		((S)errors).addActionListener(this);
		updateGui();
	}
	
	public void actionPerformed(ActionEvent e)
	{updateGui();}
    
   	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
    	
	public Object i() throws Exception
	{return split;}
	
	public void p(Object obj) throws Exception
	{
		String cmd = (String) obj;
		if(cmd.equals("selectLast")) {selectLast();return;}
		
		throw new Exception("Unsupported command: "+cmd);
	}
    
	private void updateGui()
	{
		if(errors.isEmpty()) return;
		
		numberLabel.setText(" "+errors.size());
		model.fireTableDataChanged();
	}
	
	private void selectLast() throws Exception
	{
		int count = table.getRowCount();
		if(count==0) return;
		table.getSelectionModel().setSelectionInterval(count-1, count-1);
	}
	
	private void selectionChanged()
	{
		try
		{
			int selectedRow = table.getSelectedRow();
			if(selectedRow==-1)
			{
				if(previousSelectedRow!=-1)
				table.getSelectionModel().setSelectionInterval(previousSelectedRow, previousSelectedRow);
				return;
			}
			
			if(selectedRow!=previousSelectedRow)
			{
				Object[] info = (Object[]) errors.get(selectedRow);
				Exception exp = (Exception) info[2];
				
				String s = (String) exceptionToString.t(exp);
				area.setText(s);
				area.setCaretPosition(s.length());
				previousSelectedRow = selectedRow;
			}
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	private class TableModel0 extends AbstractTableModel
	{
		public int getColumnCount() {return 5;}
		public int getRowCount() {return errors.size();}
        
		public String getColumnName(int y)
		{
			if(y==0) return "time";
			if(y==1) return "source";
			if(y==2) return "id";
			if(y==3) return "type";
			if(y==4) return "message";
			return null;
		}
        
		public Class getColumnClass(int y)
		{return String.class;}
        
		public boolean isCellEditable(int x, int y)
		{return false;}

		public Object getValueAt(int x, int y)
		{
			Object[] info = (Object[]) errors.get(x);
			Exception e = (Exception)info[2];
            
			if(y==0) return timeStamp((Date)info[3]);  // time stamp
			if(y==1) return sourceName(info[0]);  // source
			if(y==2) return (String)info[1]; // id
			if(y==3) return e.getClass().getSimpleName();
			if(y==4) return e.getMessage();
			return null;
		}
	}
    
    
	private JLabel titleLabel(String title)
	{
		JLabel label = new JLabel(title);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		return label;
	}
    
	private String sourceName(Object source)
	{
		if(source==null) return "null";
		if(source instanceof Class) return ((Class)source).getSimpleName();
		return source.getClass().getSimpleName();
	}
}