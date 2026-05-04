package a.entity.gus.x.swing.comp.cust3.execute.ctrl_v;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20221111";}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JComponent comp = (JComponent) o[0];
		E ex = (E) o[1];
		
		new Holder(comp,ex);
	}
	
	private class Holder implements KeyListener, MouseListener
	{
		private E ex;
		private JComponent comp;
		
		public Holder(JComponent comp, E ex)
		{
			this.ex = ex;
			this.comp = comp;
			init(comp);
		}
		
		private void init(Component comp)
		{
			comp.setFocusable(true);
			comp.addKeyListener(this);
			comp.addMouseListener(this);
			
			if(comp instanceof Container)
			{
				Container c = (Container) comp;
				int count = c.getComponentCount();
				for(int i=0;i<count;i++) init(c.getComponent(i));
			}
		}
		
		public void keyReleased(KeyEvent evt){}
		public void keyTyped(KeyEvent evt){}
		public void keyPressed(KeyEvent evt)
		{if(evt.isControlDown() && evt.getKeyCode()==KeyEvent.VK_V) execute(ex);}
		
		public void mouseExited(MouseEvent evt) {}
		public void mouseEntered(MouseEvent evt) {}
		public void mouseReleased(MouseEvent evt) {}
		public void mouseClicked(MouseEvent evt) {}
		public void mousePressed(MouseEvent evt)
		{comp.requestFocusInWindow();}
	}
	
	private void execute(E ex)
	{
		try{ex.e();}
		catch(Exception e)
		{Outside.err(this,"execute(E)",e);}
	}
}