package a.entity.gus06.sys.autocomplete1.persist1.load;

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
		String text = (String) persist.r(key);
		JTextComponent comp = (JTextComponent) obj;
		comp.replaceSelection(text);
	}
}
