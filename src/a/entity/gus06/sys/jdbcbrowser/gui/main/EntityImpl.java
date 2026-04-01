package a.entity.gus06.sys.jdbcbrowser.gui.main;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Set;
import javax.swing.JTextArea;
import java.awt.Insets;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSplitPane;
import javax.swing.JComponent;

public class EntityImpl implements Entity, ActionListener, I, P {

	public String creationDate() {return "20190516";}


	private Service findTables;
	private Service countWhere;
	private Service tableView;
	private Service listHolder;
	
	private JPanel panel;
	private JLabel labelTitle;
	private JButton buttonRefresh;
	private JSplitPane split;
	
	private Connection cx;
	private String dbName;
	private String tableName;
	private String colName;
	private Object value;
			
	private List tables;
	private Object where;
	private Map map;
	
	
	public EntityImpl() throws Exception
	{
		findTables = Outside.service(this,"gus06.jdbc.mysql.perform.find.tablelist.db.col");
		countWhere = Outside.service(this,"gus06.jdbc.mysql.perform.select.count.where");
		
		tableView = Outside.service(this,"*gus06.jdbc.gui.tableview");
		listHolder = Outside.service(this,"*gus06.sys.jdbcbrowser.gui.list");
		
		map = new HashMap();
		
		labelTitle = new JLabel(" ");
		labelTitle.setHorizontalAlignment(JLabel.CENTER);
		labelTitle.setBorder(BorderFactory.createRaisedBevelBorder());
		
		buttonRefresh = new JButton("Refresh");
		buttonRefresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{refresh();}
		});
		
		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(200);
		
		split.setRightComponent((JComponent) tableView.i());
		split.setLeftComponent((JComponent) listHolder.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add(labelTitle,BorderLayout.NORTH);
		panel.add(split,BorderLayout.CENTER);
		panel.add(buttonRefresh,BorderLayout.SOUTH);
		
		listHolder.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
		
		cx = (Connection) o[0];
		dbName = (String) o[1];
		tableName = (String) o[2];
		colName = (String) o[3];
		value = o[4];
		
		refresh();
	}
	
	
	private void refresh()
	{
		try
		{
			tables = (List) findTables.t(new Object[]{cx,dbName,colName});
			where = new Object[]{colName,value};
			
			labelTitle.setText(colName+"="+value);
			
			map = new HashMap();
			
			for(int i=0;i<tables.size();i++)
			{
				String table = (String) tables.get(i);
				String path = dbName+"."+table;
				
				Integer count = (Integer) countWhere.t(new Object[]{cx,path,where});
				if(count!=null && count>0) map.put(table,count);
			}
			
			listHolder.p(map);
			tableView.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}


	public void actionPerformed(ActionEvent e)
	{select();}
	
	
	
	private void select()
	{
		try
		{
			if(cx==null) return;
			String tableName = (String) listHolder.g();
			if(tableName==null) return;
			
			tableView.p(new Object[]{cx,dbName,tableName,where});
		}
		catch(Exception e)
		{Outside.err(this,"select()",e);}
	}
}
