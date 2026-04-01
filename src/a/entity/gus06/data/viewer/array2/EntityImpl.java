package a.entity.gus06.data.viewer.array2;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


public class EntityImpl implements Entity, I, P, G, ListSelectionListener {

	public String creationDate() {return "20190713";}

	private Service viewer;
	private Service splitCust;

	private JSplitPane split;
	private JTable table;
	private JLabel label;
    
	private DefaultTableModel model;
	private Object[][] data;


	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.data.viewer.object");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
	
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.getSelectionModel().addListSelectionListener(this);
        
		label = new JLabel(" ");
        
		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(table),BorderLayout.CENTER);
		p.add(label,BorderLayout.SOUTH);
        
		split = new JSplitPane();
		splitCust.p(split);
		
		split.setLeftComponent(p);
		split.setRightComponent((JComponent) viewer.i());
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (Object[][]) obj;
		if(data==null) resetGui();
		else updateGui();
	}
	
	
	private void updateGui() throws Exception
	{
		DefaultTableModel model = new DefaultTableModel(data,columns()){
			public boolean isCellEditable(int row, int column) {return false;}
		};
		table.setModel(model);
		
		int x = data.length;
		int y = x==0 ? 0 : data[0].length;
		
		label.setText(" "+x+" "+y);
		viewer.p(null);
	}
	
	
	private void resetGui() throws Exception
	{
		table.setModel(new DefaultTableModel());
		label.setText(" ");
		viewer.p(null);
	}
		
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
    
    
    

	private void selectionChanged()
	{
		try
		{
			if(table.getSelectionModel().isSelectionEmpty()) {viewer.p(null);return;}
			
			int x = table.getSelectedRow();
			int y = table.getSelectedColumn();
			Object value = table.getValueAt(x,y);
			
			viewer.p(value);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	private Object[] columns()
	{
		if(data==null || data.length==0) return new Object[]{};
		int nb = data[0].length;
		Object[] columns = new Object[nb];
		for(int i=0;i<nb;i++) columns[i] = ""+i;
		return columns;
	}
}
