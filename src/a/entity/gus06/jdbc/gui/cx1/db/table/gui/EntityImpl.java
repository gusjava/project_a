package a.entity.gus06.jdbc.gui.cx1.db.table.gui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class EntityImpl implements Entity, P, I, ActionListener {

	public String creationDate() {return "20150622";}


	private Service tableView;
	private Service updater;
	
	private JPanel panel;
	private JLabel titleLabel;
	
	private G holder;
	private String dbName;
	private String tableName;
	
	
	

	public EntityImpl() throws Exception
	{
		tableView = Outside.service(this,"*gus06.jdbc.gui.tableview");
		updater = Outside.service(this,"gus06.feature.thread.p");
		
		titleLabel = new JLabel(" ");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setBorder(BorderFactory.createRaisedBevelBorder());

		panel = new JPanel(new BorderLayout());
		panel.add(titleLabel,BorderLayout.NORTH);
		panel.add((JComponent) tableView.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{
			setHolder(null);
			dbName = null;
			tableName = null;
			
			resetGui();
			return;
		}
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		setHolder(o[0]);
		dbName = (String) o[1];
		tableName = (String) o[2];
		
		updateGui();
	}
	
	
	
	private void setHolder(Object obj) throws Exception
	{
		if(holder!=null) ((S) holder).removeActionListener(this);
		holder = (G) obj;
		if(holder!=null) ((S) holder).addActionListener(this);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	
	private void updateGui()
	{
		try
		{
			titleLabel.setText(dbName+"."+tableName);
			Object[] data = new Object[]{holder,dbName,tableName};
			updater.p(new Object[]{tableView,data});
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	
	private void resetGui()
	{
		try
		{
			titleLabel.setText(" ");
			tableView.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"resetGui()",e);}
	}
}
