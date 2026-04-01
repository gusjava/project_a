package a.entity.gus06.jdbc.gui.selector.path;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.util.Set;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.util.Vector;
import java.util.Collections;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import java.awt.GridLayout;

public class EntityImpl extends S1 implements Entity, I, P, G {

	public String creationDate() {return "20161114";}


	private Service selectorDb;
	private Service selectorTable;
	private Service formatName;

	private JPanel panel;
	private Connection cx;


	public EntityImpl() throws Exception
	{
		selectorDb = Outside.service(this,"*gus06.jdbc.gui.selector.db");
		selectorTable = Outside.service(this,"*gus06.jdbc.gui.selector.table");
		formatName = Outside.service(this,"gus06.jdbc.mysql.format.sql.name");
		
		panel = new JPanel(new GridLayout(1,2,5,5));
		panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		panel.add((JComponent) selectorDb.i());
		panel.add((JComponent) selectorTable.i());
		
        	selectorDb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{dbChanged();}
		});
		selectorTable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{tableChanged();}
		});
	}
	
	
	public Object i() throws Exception
	{return panel;}


	
	public Object g() throws Exception
	{
		String dbName = (String) selectorDb.g();
		String tableName = (String) selectorTable.g();
		return formatName(dbName)+"."+formatName(tableName);
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		cx = (Connection) obj;
		
		selectorDb.p(cx);
		selectorTable.p(null);
	}
	
	
	
	
	
	
	private void dbChanged()
	{
		try
		{
			String dbName = (String) selectorDb.g();
			selectorTable.p(new Object[]{cx,dbName});
		}
		catch(Exception e)
		{Outside.err(this,"dbChanged()",e);}
	}
	
	
	private void tableChanged()
	{
		try
		{
			selected();
		}
		catch(Exception e)
		{Outside.err(this,"tableChanged()",e);}
	}
	
	
	private void selected()
	{send(this,"selected()");}
	
	
	private String formatName(String name) throws Exception
	{return (String) formatName.t(name);}
}
