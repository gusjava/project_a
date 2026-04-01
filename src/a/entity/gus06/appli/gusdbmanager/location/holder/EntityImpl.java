package a.entity.gus06.appli.gusdbmanager.location.holder;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, P, G {

	public String creationDate() {return "20150613";}


	private Service persister;

	public EntityImpl() throws Exception
	{
		persister = Outside.service(this,"gus06.app.persister1");
	}


	

	public void p(Object obj) throws Exception
	{
		String newLocation = (String) obj;
		String location = location();
		if(equals(newLocation,location)) return;

		setLocation(newLocation);
		dataModified();
	}
	



	public Object g() throws Exception
	{return location();}
	
	
	
	
	
	private String location() throws Exception
	{return (String) persister.r(getClass().getName()+"_location");}

	private void setLocation(String location) throws Exception
	{persister.v(getClass().getName()+"_location",location);}

	
	
	
	private boolean equals(String s1, String s2)
	{
		if(s1==null && s2==null) return true;
		if(s1==null || s2==null) return false;
		return s1.equals(s2);
	}
	
	
	private void dataModified()
	{send(this,"dataModified()");}
}
