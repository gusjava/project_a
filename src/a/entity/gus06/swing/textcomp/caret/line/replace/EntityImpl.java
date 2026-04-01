package a.entity.gus06.swing.textcomp.caret.line.replace;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Vector;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20231029";}


	private Service getRange;

	public EntityImpl() throws Exception
	{
		getRange = Outside.service(this,"gus06.swing.textcomp.caret.line.range");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		int pos = comp.getCaretPosition();
		int[] range = (int[]) getRange.t(comp);
		
		int start = range[0];
		int end = range[1];
		
		int newPos = start+key.length();
		
		String text = comp.getText();
		String firstPart = text.substring(0,start);
		String lastPart = text.substring(end-1);
		
		StringBuffer b =  new StringBuffer();
		b.append(firstPart);
		b.append(key);
		b.append(lastPart);
		String newText = b.toString();
		
		comp.setText(newText);
		comp.setCaretPosition(newPos);
	}
}