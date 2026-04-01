package a.entity.gus06.jdbc.gui.sqlquery1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSplitPane;
import java.sql.Connection;
import java.awt.Color;
import java.sql.ResultSet;

public class EntityImpl implements Entity, ActionListener, I, P, R, Runnable {

	public String creationDate() {return "20150622";}
	
	public static final String MESSAGE1 = "Send query";
	public static final String MESSAGE2 = "Sending ...";


	private Service inputArea;
	private Service outputArea;
	private Service executeSql;
	private Service rsToString;

	private JPanel panel;
	private JSplitPane split;
	private JButton button;
	
	private G holder;
	private Thread t;
	
	
	

	public EntityImpl() throws Exception
	{
		inputArea = Outside.service(this,"*gus06.jdbc.gui.sqlquery1.inputarea");
		outputArea = Outside.service(this,"*gus06.jdbc.gui.sqlquery1.outputarea");
		executeSql = Outside.service(this,"gus06.jdbc.mysql.perform.sqlexecute");
		rsToString = Outside.service(this,"gus06.jdbc.gui.sqlquery1.rstostring");
		
		button = new JButton(MESSAGE1);
		button.addActionListener(this);
		inputArea.addActionListener(this);
		
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		split.setDividerLocation(200);
		split.setLeftComponent((JComponent) inputArea.i());
		split.setRightComponent((JComponent) outputArea.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add(split,BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		holder = (G) obj;
	}



	public void actionPerformed(ActionEvent e)
	{launch();}
	
	
	private void launch()
	{
		if(t!=null && t.isAlive()) return;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	
	
	
	public void run()
	{
		button.setForeground(Color.BLUE);
		button.setFont(button.getFont().deriveFont(Font.BOLD));
		button.setText(MESSAGE2);
		
		perform();
		
		button.setForeground(Color.BLACK);
		button.setFont(button.getFont().deriveFont(Font.PLAIN));
		button.setText(MESSAGE1);
	}
	
	
	
	
	private void perform()
	{
		try
		{
			if(holder==null) return;
			String sql = (String) inputArea.g();
			if(sql.equals("")) return;
			
			String rs = executeSql(sql);
			outputArea.p(rs);
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
	
	
	
	
	private String executeSql(String sql)
	{
		try
		{
			Connection cx = (Connection) holder.g();
			Object res = executeSql.t(new Object[]{cx,sql});
			
			if(res==null) return null;
			if(res instanceof ResultSet) return (String) rsToString.t(res);
			return res.toString();
		}
		catch(Exception e)
		{
			Outside.err(this,"executeSql(String)",e);
			return e.toString();
		}
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("inputArea")) return inputArea.r("area");
		if(key.equals("outputArea")) return outputArea.r("area");
		if(key.equals("keys")) return new String[]{"inputArea","outputArea"};
		
		throw new Exception("Unknown key: "+key);
	}
}
