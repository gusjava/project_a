package a.entity.gus06.sys.autocomplete1.persist1.save;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20160425";}


	private Service persist;

	public EntityImpl() throws Exception
	{
		persist = Outside.service(this,"gus06.sys.autocomplete1.persist1.persister");
	}

	
	public void p(Object obj) throws Exception
	{v("default",obj);}
	
	
	public void v(String key, Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String text = getText(comp);
		persist.v(key,text);
	}
	
	private String getText(JTextComponent comp)
	{
		String s = comp.getSelectedText();
		if(s!=null && !s.equals("")) return s;
		return comp.getText();
	}
}
