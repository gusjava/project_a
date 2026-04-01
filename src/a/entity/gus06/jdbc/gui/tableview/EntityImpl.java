package a.entity.gus06.jdbc.gui.tableview;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;

public class EntityImpl implements Entity, ActionListener, I, P {

	public String creationDate() {return "20150623";}


	private Service control;
	private Service tableHolder;
	private Service structHolder;
	
	private JTabbedPane tab;
	private JTable table;
	private JLabel numberLabel;
	
	private Object data;


	public EntityImpl() throws Exception
	{
		control = Outside.service(this,"*gus06.jdbc.gui.tableview.control");
		tableHolder = Outside.service(this,"*gus06.jdbc.gui.tableview.table");
		structHolder = Outside.service(this,"*gus06.jdbc.gui.tableview.structure");
		
		table = (JTable) tableHolder.i();
		numberLabel = new JLabel(" ");
		control.v("table",table);
		
		JPanel p_number = new JPanel(new BorderLayout());
		p_number.add(numberLabel,BorderLayout.SOUTH);
		
		JPanel p_bottom = new JPanel(new BorderLayout());
		p_bottom.setBorder(BorderFactory.createRaisedBevelBorder());
		p_bottom.add(p_number,BorderLayout.WEST);
		p_bottom.add((JComponent) control.i(),BorderLayout.CENTER);
		
		JPanel panelData = new JPanel(new BorderLayout());
		panelData.add(new JScrollPane(table),BorderLayout.CENTER);
		panelData.add(p_bottom,BorderLayout.SOUTH);
		
		JPanel panelStruct = (JPanel) structHolder.i();
		
		tab = new JTabbedPane();
		
		tab.addTab("Data",panelData);
		tab.addTab("Structure",panelStruct);
		
		control.addActionListener(this);
		
		tableHolder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String s = e.getActionCommand();
				if(s.equals("modified()")) updateNumberLabel();
				
				else if(s.equals("doubleClick()")) doubleClick();
				
				else if(s.equals("keyDelete()")) keyDelete();
				else if(s.equals("keyF1()")) keyF1();
				else if(s.equals("keyF2()")) keyF2();
				else if(s.equals("keyF5()")) keyF5();
				
				else if(s.equals("keyCtrlV()")) keyCtrlV();
				else if(s.equals("keyCtrlDelete()")) keyCtrlDelete();
				
				else if(s.equals("keyAltDelete()")) keyAltDelete();
			}
		});
	}
	
	
	
	public Object i() throws Exception
	{return tab;}
	
	
	
	public void p(Object obj) throws Exception
	{
		data = obj;
		
		tableHolder.p(data);
		control.p(data);
		structHolder.p(data);
		
		updateGui();
	}


	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	private void updateGui()
	{
		try
		{
			tableHolder.e();
			structHolder.e();
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	
	
	
	
	private void updateNumberLabel()
	{
		numberLabel.setText(numberInfo()+"   ");
	}
	
	private String numberInfo()
	{
		int rowNb = table.getRowCount();
		int colNb = table.getColumnCount();
		if(colNb==0) return "";
		return "["+rowNb+","+colNb+"]";
	}
	
	
	
	private void keyDelete()
	{
		try{control.v("perform","cellSetNull");}
		catch(Exception e)
		{Outside.err(this,"keyDelete()",e);}
	}
	
	private void keyCtrlDelete()
	{
		try{control.v("perform","rowDelete");}
		catch(Exception e)
		{Outside.err(this,"keyCtrlDelete()",e);}
	}
	
	private void keyAltDelete()
	{
		try{control.v("perform","tableDelete");}
		catch(Exception e)
		{Outside.err(this,"keyAltDelete()",e);}
	}
	
	private void keyF1()
	{
		try{control.v("perform","cellEdit");}
		catch(Exception e)
		{Outside.err(this,"keyF1()",e);}
	}
	
	private void keyF2()
	{
//		try{control.v("perform","cellWatcher");}
//		catch(Exception e)
//		{Outside.err(this,"keyF2()",e);}
	}
	
	private void keyF5()
	{
		try{control.v("perform","tableReload");}
		catch(Exception e)
		{Outside.err(this,"keyF5()",e);}
	}
	
	private void keyCtrlV()
	{
		try{control.v("perform","cellPaste");}
		catch(Exception e)
		{Outside.err(this,"keyCtrlV()",e);}
	}
	
	private void doubleClick()
	{
		try{control.v("perform","cellEdit");}
		catch(Exception e)
		{Outside.err(this,"doubleClick()",e);}
	}
}