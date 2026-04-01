package a.entity.gus06.swing.textcomp.autocomplete.entity.addtrycatch;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20140825";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String text = comp.getText();
		int pos = comp.getCaretPosition();
		
		StringBuffer b = new StringBuffer();
		
		b.append("try\n");
		b.append("\t\t{\n");
		b.append("\t\t\t\n");
		
		int delta = b.length()-1;
		
		b.append("\t\t}\n");
		b.append("\t\tcatch(Exception e)\n");
		b.append("\t\t{Outside.err(this,\"\",e);}");
		
		comp.getDocument().insertString(pos,b.toString(),null);
		comp.setCaretPosition(pos+delta);
	}
	
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String text = comp.getText();
		int pos = comp.getCaretPosition();
		
		StringBuffer b = new StringBuffer();
		
		b.append("private void "+key+"()\n");
		b.append("\t{\n");
		b.append("\t\ttry\n");
		b.append("\t\t{\n");
		b.append("\t\t\t\n");
		
		int delta = b.length()-1;
		
		b.append("\t\t}\n");
		b.append("\t\tcatch(Exception e)\n");
		b.append("\t\t{Outside.err(this,\""+key+"()\",e);}\n");
		b.append("\t}\n");
		
		comp.getDocument().insertString(pos,b.toString(),null);
		comp.setCaretPosition(pos+delta);
	}
}
