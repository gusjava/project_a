package a.entity.gus06.swing.textfield.factory.recallfield;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150323";}
	
	
	public Object i() throws Exception
	{return new JTextField0();}
	
	
	
	
	public class JTextField0 extends JTextField implements KeyListener
	{
		private String previous;
		
		public JTextField0()
		{
			super();
			addKeyListener(this);
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
		public void keyPressed(KeyEvent e)
		{
			int c = e.getKeyCode();
			if(c==KeyEvent.VK_ESCAPE) setText("");
			else if(c==KeyEvent.VK_F1 && previous!=null) setText(previous);
		}
	
		public String getText()
		{
			String t = super.getText();
			if(!t.equals("")) previous = t;
			return t;
		}
	}
	
}
