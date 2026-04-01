package a.entity.gus06.swing.combobox.persister.index;

import a.framework.*;
import javax.swing.JComboBox;


public class EntityImpl implements Entity, V {

	public String creationDate() {return "20150509";}


	private Service manager;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.app.persister1.manager");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		final JComboBox comp = (JComboBox) obj;
		
		String text = (String) manager.r(key);
		if(isInt(text)) comp.setSelectedIndex(toInt(text));
		
		manager.v(key,new G(){
			public Object g() throws Exception {return ""+comp.getSelectedIndex();}
		});
	}
	
	
	
	
	private boolean isInt(String s)
	{
		if(s==null) return false;
		try{Integer.parseInt(s);}
		catch(NumberFormatException e) {return false;}
		return true;
	}
	
	private int toInt(String s)
	{
		try{return Integer.parseInt(s);}
		catch(NumberFormatException e) {return 0;}
	}
}
