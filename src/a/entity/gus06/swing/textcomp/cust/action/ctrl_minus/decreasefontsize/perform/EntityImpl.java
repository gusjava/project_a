package a.entity.gus06.swing.textcomp.cust.action.ctrl_minus.decreasefontsize.perform;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.awt.Font;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141105";}


	public void p(Object obj) throws Exception
	{perform((JTextComponent) obj);}
	
	private void perform(JTextComponent comp) throws Exception
	{
		Font font = comp.getFont();
		int size = font.getSize();
		comp.setFont(font.deriveFont((float)(size-1)));
	}
}
