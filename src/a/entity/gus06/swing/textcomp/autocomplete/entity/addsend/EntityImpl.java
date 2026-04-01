package a.entity.gus06.swing.textcomp.autocomplete.entity.addsend;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20201106";}

	
	
	public void p(Object obj) throws Exception
	{v("performed",obj);}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String text = comp.getText();
		int pos = comp.getCaretPosition();
		
		StringBuffer b = new StringBuffer();
		
		b.append("private void "+key+"()\n");
		b.append("\t{send(this,\""+key+"()\");}\n");
		
		int delta = b.length()-1;
		
		comp.getDocument().insertString(pos,b.toString(),null);
		comp.setCaretPosition(pos+delta);
	}
}