package a.entity.gus.y.autocomplete1.caret.word.replace;

import javax.swing.text.JTextComponent;
import a.framework.*;

public class EntityImpl implements Entity, V {
	public String creationDate() {return "20240714";}
	
	public static final String CARET = "<CARET>";

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
		
		int newPos = range[0]+1;
		int index0 = key.indexOf(CARET);
		if(index0<0) newPos+=key.length();
		else newPos+=index0;
		
		String text = comp.getText();
		String before = text.substring(0,range[0]+1);
		String after = text.substring(range[1]);
		String replacement = key.replace(CARET,"");
		String newText = before + replacement + after;
		
		comp.setText(newText);
		comp.setCaretPosition(newPos);
	}
}
