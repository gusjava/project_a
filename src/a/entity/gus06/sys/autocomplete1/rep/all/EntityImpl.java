package a.entity.gus06.sys.autocomplete1.rep.all;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20160519";}


	private Service clipboard;

	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus.x.clipboard.string");
	}

	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		
		String selected = comp.getSelectedText();
		if(selected==null || selected.equals("")) return;
		
		String text = comp.getText();
		int pos = comp.getCaretPosition();
		
		text = text.replace(selected,key);
		
		pos = Math.min(pos,text.length());
		comp.setText(text);
		comp.setCaretPosition(pos);
	}
	
	
	public void p(Object obj) throws Exception
	{
		String value = (String) clipboard.g();
		v(value,obj);
	}
}
