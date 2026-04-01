package a.entity.gus06.swing.tree.cust.file.action.ctrl_f.search;

import a.framework.*;
import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractAction;
import javax.swing.JTree;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20200101";}

	public static final String DEFAULT_KEY = "ctrl f";
	
	
	private Service perform;
	private Service manageKeyStroke;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.tree.perform.file.search");
		manageKeyStroke = Outside.service(this,"gus06.sys.keystroke1.manager");
	}
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((JTree) obj);}



	private class Holder extends AbstractAction implements E, Runnable
	{
		private JTree comp;
		private Thread t;
		
		public Holder(JTree comp) throws Exception
		{
			this.comp = comp;
			manageKeyStroke.p(new Object[]{id(),DEFAULT_KEY,comp,this});
		}
		public void actionPerformed(ActionEvent e)
		{start();}
		
		public void e() throws Exception
		{start();}
		
		private void start()
		{
			if(t!=null && t.isAlive()) return;
			t = new Thread(this, "THREAD_"+EntityImpl.class.getName());
			t.start();
		}
		
		public void run()
		{perform(comp);}
	}
	
	
	private void perform(JTree comp)
	{
		try
		{
			((V)comp).v("searching",true);
			comp.repaint();
			
			perform.p(comp);
			
			((V)comp).v("searching",false);
			comp.repaint();
		}
		catch(Exception e)
		{Outside.err(this,"perform(JTree)",e);}
	}
	
	private String id()
	{return getClass().getName();}
}