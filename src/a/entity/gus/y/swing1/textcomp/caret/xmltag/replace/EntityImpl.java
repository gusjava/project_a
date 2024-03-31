package a.entity.gus.y.swing1.textcomp.caret.xmltag.replace;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.Vector;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20240121";}


	private Service getRange;

	public EntityImpl() throws Exception
	{
		getRange = Outside.service(this,"gus.y.swing1.textcomp.caret.xmltag.range");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		int[] range = (int[]) getRange.t(comp);
		
		String text = comp.getText();
		String newText = text.substring(0,range[0]+1) + key + text.substring(range[1]);
		
		comp.setText(newText);
		comp.setCaretPosition(range[0]+1+key.length());
	}
}
