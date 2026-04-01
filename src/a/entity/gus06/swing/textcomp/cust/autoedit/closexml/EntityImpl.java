package a.entity.gus06.swing.textcomp.cust.autoedit.closexml;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.SwingUtilities;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170130";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.textcomp.cust.autoedit.closexml.perform");
	}



	public void p(Object obj) throws Exception
	{new Holder1((JTextComponent) obj);}




	private class Holder1 implements KeyListener
	{
		private JTextComponent comp;
		public Holder1(JTextComponent comp)
		{
			this.comp = comp;
			comp.addKeyListener(this);
		}
		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e)
		{SwingUtilities.invokeLater(new Holder2(comp,e));}
	}
	
	
	
	private class Holder2 implements Runnable
	{
		private JTextComponent comp;
		private KeyEvent evt;
		
		public Holder2(JTextComponent comp, KeyEvent evt)
		{
			this.comp = comp;
			this.evt = evt;
		}
		public void run()
		{perform(comp,evt);}
	}
	
	
	


	private void perform(JTextComponent comp, KeyEvent evt)
	{
		try
		{
			int code = evt.getKeyCode();
			switch(code)
			{
				case KeyEvent.VK_COLON: perform.p(comp);break;
			}
		}
		catch(Exception e)
		{Outside.err(this,"perform(JTextComponent,KeyEvent)",e);}
	}
}
