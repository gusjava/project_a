package a.entity.gus06.appli.gusdbmanager.gui.cx.bottombar.userlabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JLabel;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150613";}


	private Service userDisplay;
	private Service repaint;
	

	public EntityImpl() throws Exception
	{
		userDisplay = Outside.service(this,"gus06.jdbc.connection.grants.display");
		repaint = Outside.service(this,"gus06.swing.label.cust2.display");
	}



	public Object t(Object obj) throws Exception
	{return new JLabel1(obj);}


	
	
	private class JLabel1 extends JLabel implements ActionListener
	{
		private Object holder;
		
		public JLabel1(Object holder) throws Exception
		{
			super(" ");
			this.holder = holder;
			((S) holder).addActionListener(this);
			updateGui(this,holder);
		}
		
		public void actionPerformed(ActionEvent e)
		{updateGui(this,holder);}
	}
	
	
	
	
	private void updateGui(JLabel1 label, Object holder)
	{
		try
		{
			if(connected(holder))
			repaint.v(display(holder),label);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui(JLabel1,Object)",e);}
	}
	
	
	
	
	
	
	private String display(Object holder) throws Exception
	{return (String) userDisplay.t(cx(holder));}
	
	
	private boolean connected(Object holder) throws Exception
	{return ((F) holder).f(null);}
	
	
	private Connection cx(Object holder) throws Exception
	{return (Connection) ((R) holder).r("cx");}
}
