package a.entity.gus06.swing.textcomp.cust.action.ctrl_r.keepline;

import a.framework.*;
import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractAction;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141113";}

	public static final String DEFAULT_KEY = "ctrl r";
	
	
	private Service perform;
	private Service manageKeyStroke;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_r.keepline.perform");
		manageKeyStroke = Outside.service(this,"gus06.sys.keystroke1.manager");
	}
	
	
	public void p(Object obj) throws Exception
	{new Holder((JTextComponent) obj);}



	private class Holder extends AbstractAction implements Runnable
	{
		private JTextComponent comp;
		private Thread t;
		
		public Holder(JTextComponent comp) throws Exception
		{
			this.comp = comp;
			manageKeyStroke.p(new Object[]{id(),DEFAULT_KEY,comp,this});
		}
		public void actionPerformed(ActionEvent e)
		{
			//if(t!=null && t.isAlive()) return;
			//t = new Thread(this,"THREAD_"+getClass().getName());
			//t.start();
			
			perform(comp);
		}
		
		public void run()
		{perform(comp);}
	}
	
	
	private void perform(JTextComponent comp)
	{
		try{perform.p(comp);}
		catch(Exception e)
		{Outside.err(this,"perform(JTextComponent)",e);}
	}
	
	private String id()
	{return getClass().getName();}
}
