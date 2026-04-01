package a.entity.gus06.swing.textcomp.autocomplete.entity.addthrows;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, V, P {

	public String creationDate() {return "20140825";}

	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		int pos = comp.getCaretPosition();
		comp.getDocument().insertString(pos,"throws Exception",null);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String text = comp.getText();
		int pos = comp.getCaretPosition();
		
		StringBuffer b = new StringBuffer();
		
		b.append("private void "+key+"() throws Exception\n");
		b.append("\t{\n");
		b.append("\t\t\n");
		
		int delta = b.length()-1;
		
		b.append("\t}\n");
		
		comp.getDocument().insertString(pos,b.toString(),null);
		comp.setCaretPosition(pos+delta);
	}
}
