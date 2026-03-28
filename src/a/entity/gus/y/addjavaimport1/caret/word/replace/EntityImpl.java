package a.entity.gus.y.addjavaimport1.caret.word.replace;

import javax.swing.text.JTextComponent;
import a.framework.*;

public class EntityImpl implements Entity, V {
	public String creationDate() {return "20240712";}

	private Service getRange;

	public EntityImpl() throws Exception
	{
		getRange = Outside.service(this,"gus.y.addjavaimport1.caret.word.range");
	}
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		int pos = comp.getCaretPosition();
		int[] range = (int[]) getRange.t(comp);
		
		int newPos = range[0]+1+key.length();
		
		String text = comp.getText();
		String newText = text.substring(0,range[0]+1) + key + text.substring(range[1]);
		
		comp.setText(newText);
		comp.setCaretPosition(newPos);
	}
}
