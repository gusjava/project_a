package a.entity.gus06.appli.gusclient1.gui.entity.holder;

import a.framework.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class EntityImpl extends S1 implements Entity, P, G {

	public String creationDate() {return "20140731";}



	private String name = null;

	public EntityImpl() throws Exception
	{
		
	}
	
	public Object g() throws Exception
	{return name;}
	
	
	public void p(Object obj) throws Exception
	{
		String newName = (String) obj;
		if(equals(newName,name)) return;
		
		name = newName;
		changed();
	}
	
	
	private boolean equals(String s1, String s2)
	{
		if(s1==null && s2==null) return true;
		if(s1==null || s2==null) return false;
		return s1.equals(s2);
	}
	
	
	private void changed()
	{send(this,"changed()");}
}
