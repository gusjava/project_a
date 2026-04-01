package a.entity.gus06.x.swing.textcomp.build.caretpositionlabel;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251116";}

	public Object t(Object obj) throws Exception
	{return new JLabel1((JTextComponent) obj);}

	public class JLabel1 extends JLabel implements CaretListener, MouseMotionListener
	{
		private JTextComponent comp;
		
		public JLabel1(JTextComponent comp) throws Exception
		{
			super();
			setFont(getFont().deriveFont(Font.PLAIN));
			this.comp = comp;
			comp.addCaretListener(this);
			comp.addMouseMotionListener(this);
			update();
		}
	
		private void update()
		{
			int pos = comp.getCaretPosition();
			int select = comp.getCaret().getDot() - comp.getCaret().getMark();
			if(select!=0) setText(" "+pos+" ["+select+"] ");
			else setText(" "+pos+" ");
		}
	
		public void caretUpdate(CaretEvent e) {update();}
		public void mouseDragged(MouseEvent e) {update();}
		public void mouseMoved(MouseEvent e) {}
	}
}
