package a.entity.gus06.sys.autocomplete1.xmltag.wrap;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20160425";}

	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String text = comp.getSelectedText();
		if(text.trim().equals("")) return;
		
		int k=0;
		StringBuffer h = new StringBuffer();
		while(text.charAt(k)=='\t') {h.append("\t");k++;}
		
		StringBuffer b = new StringBuffer();
		
		b.append(h.toString());
		b.append("<");
		b.append(key);
		b.append(">\n");
		
		b.append(text+"\n");
		
		b.append(h.toString());
		b.append("</");
		b.append(key);
		b.append(">\n");
		
		comp.replaceSelection(b.toString());
	}
}
