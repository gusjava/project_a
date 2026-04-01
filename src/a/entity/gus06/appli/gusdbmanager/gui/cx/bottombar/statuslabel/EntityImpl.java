package a.entity.gus06.appli.gusdbmanager.gui.cx.bottombar.statuslabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150613";}


	private Service statusDisplay;
	private Service repaint;
	

	public EntityImpl() throws Exception
	{
		statusDisplay = Outside.service(this,"gus06.appli.gusdbmanager.connection.statusdisplay");
		repaint = Outside.service(this,"gus06.swing.label.cust2.icon.tooltip");
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
			String status = status(holder);
			String display = display(status);
			repaint.v(display,label);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui(JLabel1,Object)",e);}
	}
	
	
	
	
	
	
	private String display(String status) throws Exception
	{return (String) statusDisplay.t(status);}
	
	
	private String status(Object holder) throws Exception
	{return (String) ((R) holder).r("status");}
	
	
}
