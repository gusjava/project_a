package a.entity.gus06.swing.spinner.persister.index;

import a.framework.*;
import javax.swing.JSpinner;


public class EntityImpl implements Entity, V {

	public String creationDate() {return "20170924";}


	private Service manager;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.app.persister1.manager");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		final JSpinner comp = (JSpinner) obj;
		
		String text = (String) manager.r(key);
		if(isInt(text)) comp.setValue(toInt(text));
		
		manager.v(key,new G(){
			public Object g() throws Exception {return ""+comp.getValue();}
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
