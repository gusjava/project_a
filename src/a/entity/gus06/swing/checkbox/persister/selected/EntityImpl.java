package a.entity.gus06.swing.checkbox.persister.selected;

import a.framework.*;
import javax.swing.JCheckBox;


public class EntityImpl implements Entity, V {

	public String creationDate() {return "20170924";}


	private Service manager;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.app.persister1.manager");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		final JCheckBox comp = (JCheckBox) obj;
		
		String text = (String) manager.r(key);
		if(isBool(text)) comp.setSelected(toBool(text));
		
		manager.v(key,new G(){
			public Object g() throws Exception {return ""+comp.isSelected();}
		});
	}
	
	
	
	
	private boolean isBool(String s)
	{
		if(s==null) return false;
		return s.equals("true") || s.equals("false");
	}
	
	private boolean toBool(String s)
	{
		return Boolean.parseBoolean(s);
	}
}
