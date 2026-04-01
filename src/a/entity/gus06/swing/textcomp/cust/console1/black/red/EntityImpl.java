package a.entity.gus06.swing.textcomp.cust.console1.black.red;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.text.JTextComponent;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140730";}

	public static final Font FONT = new Font("Courier",Font.PLAIN,14);
	public static final Insets MARGIN = new Insets(3,3,3,3);
	public static final Color COLOR1 = Color.BLACK;
	public static final Color COLOR2 = Color.RED;
	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent c = (JTextComponent) obj;
		
		c.setFont(FONT);
		c.setMargin(MARGIN);
		c.setBackground(COLOR1);
		c.setForeground(COLOR2);
		c.setCaretColor(COLOR2);
	}
}
