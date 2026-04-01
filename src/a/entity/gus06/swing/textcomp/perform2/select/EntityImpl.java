package a.entity.gus06.swing.textcomp.perform2.select;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.regex.*;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20140825";}

	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String text = comp.getText();
		
		Pattern p = Pattern.compile(Pattern.quote(key));
		Matcher m = p.matcher(text);
		
		if(!m.find()) return;
		
		comp.requestFocusInWindow();
		comp.select(m.start(),m.end());
	}
}
