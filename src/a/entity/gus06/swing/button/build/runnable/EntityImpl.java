package a.entity.gus06.swing.button.build.runnable;

import a.framework.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161108";}


	private Service findRunnable;
	
	public EntityImpl() throws Exception
	{
		findRunnable = Outside.service(this,"gus06.find.runnable");
	}

	
	public Object t(Object obj) throws Exception
	{
		Runnable r = (Runnable) findRunnable.t(obj);
		return new JButton1(r);
	}
	
	private class JButton1 extends JButton implements Runnable, ActionListener
	{
		private Runnable r;
		private Thread t;
		
		public JButton1(Runnable r)
		{
			super();
			this.r = r;
			addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(t!=null && t.isAlive()) return;
			t = new Thread(this,"THREAD_"+getClass().getName());
			t.start();
		}

		public void run()
		{
			setEnabled(false);
			r.run();
			setEnabled(true);
		}
	}	
}
