package a.entity.gus06.swing.textcomp.cust.action.alt_q.keepselection.perform.caret;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160902";}



	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String text = comp.getText();
		String[] lines = text.split("\n");
		int nb = lines.length;
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<nb;i++) b.append("\n");
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		comp.setText(b.toString());
	}
}
