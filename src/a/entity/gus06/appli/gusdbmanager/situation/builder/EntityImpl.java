package a.entity.gus06.appli.gusdbmanager.situation.builder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, ActionListener {

	public String creationDate() {return "20150613";}

	private Service locationHolder;
	private Service isOnline;
	private Service isLan;
	
	private String computerName;


	
	public EntityImpl() throws Exception
	{
		locationHolder = Outside.service(this,"gus06.appli.gusdbmanager.location.holder");
		isOnline = Outside.service(this,"gus06.watching.online");
		isLan = Outside.service(this,"gus06.watching.lan");
		
		computerName = System.getenv("COMPUTERNAME");
		
		locationHolder.addActionListener(this);
		isOnline.addActionListener(this);
		isLan.addActionListener(this);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{situationChanged();}

	
	
	private void situationChanged()
	{send(this,"situationChanged()");}
	
	


	public Object g() throws Exception
	{return computerName+"|"+location()+"|"+online();}


	
	
	private String online() throws Exception
	{
		if(!isOnline.f(null)) return "false";
		return "wan";
	}
	
	
	
	private String location() throws Exception
	{
		if(!isLan.f(null)) return "false";
		String location = (String) locationHolder.g();
		return location==null?"?":location;
	}
}
