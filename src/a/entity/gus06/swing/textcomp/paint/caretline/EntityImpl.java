package a.entity.gus06.swing.textcomp.paint.caretline;

import a.framework.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.text.JTextComponent;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140726";}

	public static final Color COLOR = new Color(250,250,204);

	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof P)
		((P) obj).p(new Painter());
	}


	private class Painter implements P
	{
		public void p(Object obj) throws Exception
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);

			Graphics g = (Graphics) o[0];
			JTextComponent comp = (JTextComponent) o[1];

			Rectangle rect = comp.modelToView(comp.getCaretPosition());
			if(rect==null) return;

			g.setColor(COLOR);
			g.fillRect(0,rect.y,comp.getWidth(),rect.height);
		}
	}
}