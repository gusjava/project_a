package a.entity.gus06.swing.dialog.build.dialogontop.tabbed;

import a.framework.*;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20201113";}


	private Service toComp;
	private Service getTimer;
	private Service draggable;
	private Service executeOnDel;
	private Timer timer;

	public EntityImpl() throws Exception
	{
		toComp = Outside.service(this,"gus06.swing.comp.find");
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		draggable = Outside.service(this,"gus06.swing.comp.cust.dragframe");
		executeOnDel = Outside.service(this,"gus06.swing.comp.cust3.execute.del");
		timer = (Timer) getTimer.g();
	}
	
	
	public void p(Object obj) throws Exception
	{
    		Dialog1 d = new Dialog1((JComponent) toComp.t(obj));
		d.setVisible(true);
	}
	
	
	public Object t(Object obj) throws Exception
	{
    		return new Dialog1((JComponent) toComp.t(obj));
	}
	
	
	
	
	
	private class Dialog1 extends JDialog
	{
		public Dialog1(JComponent content) throws Exception
		{
			super((JFrame)null,false);
			
			setUndecorated(true);
			setResizable(false);
			setAlwaysOnTop(true);
			
			JLabel label = new JLabel(" ");
			label.setOpaque(true);
			label.setBackground(Color.GRAY);
			
			label.setMinimumSize(new Dimension(10,0));
			label.setMaximumSize(new Dimension(10,0));
			label.setPreferredSize(new Dimension(10,0));
			
			draggable.p(label);
			executeOnDel.p(new Object[]{label,(E) this::close});
			
			JPanel panel = new JPanel(new BorderLayout());
			panel.add(content,BorderLayout.CENTER);
			panel.add(label,BorderLayout.WEST);
			
			setContentPane(panel);
			setLocationRelativeTo(null);
			
			TimerTask task = new TimerTask(){public void run(){updateGui();}};
	    		timer.schedule(task,new Date(),100);
		}
		
		private void updateGui()
		{pack();}
		
		private void close()
		{dispose();}
	}
}