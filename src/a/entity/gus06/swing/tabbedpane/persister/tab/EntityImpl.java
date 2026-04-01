package a.entity.gus06.swing.tabbedpane.persister.tab;

import a.framework.*;
import javax.swing.JTabbedPane;


public class EntityImpl implements Entity, V {

	public String creationDate() {return "20140917";}


	private Service manager;


	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.app.persister1.manager");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		final JTabbedPane comp = (JTabbedPane) obj;
		
		String text = (String) manager.r(key);
		setSelectedIndex(comp,text);
		
		manager.v(key,new G(){
			public Object g() throws Exception {return ""+comp.getSelectedIndex();}
		});
	}
	
	
	private void setSelectedIndex(JTabbedPane comp, String text)
	{
		if(!isInt(text)) return;
		
		int number = comp.getTabCount();
		if(number==0) return;
		
		int index = toInt(text);
		if(index<0) index = 0;
		if(index>=number) index = number-1;
		
		comp.setSelectedIndex(index);
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
